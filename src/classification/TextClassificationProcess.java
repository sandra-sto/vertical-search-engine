package classification;

import textdocuments_representations.terms.IndexedTermsSerializator;
import textdocuments_representations.terms.IndexedTerms;
import classification.multinomial_classifiers.ClassificationModelSerializator;
import main.IndexerSearcherFactory;
import main.LuceneIndexerSearcherFactory;
import classification.class_types.*;
import classification.evaluation.*;
import classification.inputs.*;
import classification.regularization.L2Regularization;
import java.io.IOException;
import indexing.Indexer;
import java.util.*;
import java.util.stream.Collectors;
import search.*;
import textdocuments_representations.DocumentTfIdfRepresentationCreator;

/**
 *
 * @author Sandra Stojanović
 */


//Facade class
public class TextClassificationProcess extends ClassificationProcess{
    //static final
    public static final String INDEXED_TERMS_FILE = "indexedTerms\\indexedTerms"; 
    public static final String DOCUMENTS_INDEX_DIR = "indexDir\\";
    public static final String CLASSES_INDEX_DIR = DOCUMENTS_INDEX_DIR + "classes\\";
    
    public static IndexedTerms indexedTerms;
    
    IndexerSearcherFactory indexerSearcherFactory;
    Indexer indexer;
    Searcher searcher;
    DocumentTfIdfRepresentationCreator documentReprCreator;
    ClassificationModelSerializator modelSerializator;
    IndexedTermsSerializator indexedTermsSerializator;
    
    public TextClassificationProcess(){
        super();
        indexedTerms = IndexedTerms.getInstance();
        
        indexerSearcherFactory = new LuceneIndexerSearcherFactory();  
        
        indexer = indexerSearcherFactory.createIndexer();
        searcher = indexerSearcherFactory.createSearcher();    
        documentReprCreator = indexerSearcherFactory.createDocumentTfIdfRepresentationCreator();
        
        indexedTermsSerializator = new IndexedTermsSerializator();    
    }
    
    private void indexDocuments(String filesToIndex, String indexDir){
        indexer.setFilesToIndex(filesToIndex);
        indexer.setIndexDir(indexDir);
	indexer.indexFiles();
    }
     
    public void deserializeIndexedTerms(){
        indexedTerms = indexedTermsSerializator.deserialize(INDEXED_TERMS_FILE);
    }
    
    private void serializeIndexedTerms(){
        if(indexedTerms != null)
            indexedTermsSerializator.serialize(indexedTerms, INDEXED_TERMS_FILE); 
    }
   
    public void initiateClassificationAlgorithm(){
        algorithm.makeBinaryModels(indexedTerms.getTermsNumber(), classTypes.getClassesTypes());

        MultinomialAlgorithmEvaluator evaluator = new MultinomialPrecisionRecallEvaluator();
        algorithm.setAlgorithmEvaluator(evaluator);
        algorithm.setRegularization(new L2Regularization());
        algorithm.setFeatureSelector("frequency based");
    }
    
    public List<ClassificationInput> indexDocumentsAndGetTfIdf(String documentsPath, String indexPath){
        indexDocuments(documentsPath, indexPath);
        
        documentReprCreator.setIndexDir(indexPath);  
        List<ClassificationInput> documents = documentReprCreator.createTfIdfDocumentsRepresentations();
        return documents;   
    }
    
    private List<ClassificationInput> indexDocumentsAndGetTf(String documentsPath, String indexPath){
        List<ClassificationInput> documents = null;
        
        indexDocuments(documentsPath, indexPath);
        documentReprCreator.setIndexDir(indexPath);
        try{
            documents = documentReprCreator.getTfValuesAndCreateTfIdfDocuments();//samo tf uzimam u obzir
        }catch(IOException exc){
            exc.printStackTrace();
        }  
        return documents;
    }
    
    @Override
    public List<ClassificationInput> createTestRepresentation(String testPath){        
        String indexPath = DOCUMENTS_INDEX_DIR + "test\\";
        List<ClassificationInput> documents = indexDocumentsAndGetTf(testPath, indexPath);
        return documents;
    }
    
    @Override
    public List<ClassificationInput> createTrainingRepresentation(String path) {
        String indexDir = DOCUMENTS_INDEX_DIR + "train\\";
        
        durationResults.setStartTime("indexing");
        List<ClassificationInput> documents = indexDocumentsAndGetTfIdf(path, indexDir); 
        durationResults.setEndTime();
        
        return documents;
    }

    @Override
    public List<ClassificationInput> createClassificationRepresentation(String path) {
        String classifiedDocumentsIndexDir = DOCUMENTS_INDEX_DIR + "classes\\";
        
        durationResults.setStartTime("preprocessing text classification");
        List<ClassificationInput> documents = indexDocumentsAndGetTf(path, classifiedDocumentsIndexDir);
        durationResults.setEndTime();
        
        return documents;
    }

    public void createRepresentationsAndClassify(String documentsFile){
        deserializeParameters();
        
        String classifiedDocumentsIndexDir = DOCUMENTS_INDEX_DIR + "classified\\";
        durationResults.setStartTime("indexing");
        List<ClassificationInput> documents = indexDocumentsAndGetTf(documentsFile, classifiedDocumentsIndexDir);
        durationResults.setEndTime();
        
        durationResults.setStartTime("classification of documents");
        classifyDocumentsAndMakeIndexesForClasses(documents);
        durationResults.setEndTime();
    }
  
    public void classifyDocumentsAndMakeIndexesForClasses(List<ClassificationInput> inputs){    
        inputs.stream().collect(Collectors.groupingBy(input -> classify(input).getClassType()))
                .forEach((x, y) -> makeIndexForClass(x, y));
    }
    
    public void makeIndexForClass(ClassType classType, List<ClassificationInput> documents){
        try{
            durationResults.setStartTime("creating partial indexes");
            tryMakeIndexForClass(classType, documents);
            durationResults.setEndTime();
        }catch(IOException exc){
            exc.printStackTrace();
        }
    }
    
    public void tryMakeIndexForClass(ClassType classType, List<ClassificationInput> documents) throws IOException{
        indexer.setIndexDir(CLASSES_INDEX_DIR + classType.getName());
        
        for(ClassificationInput document : documents){
            if(document instanceof DocumentInput)
                indexer.indexFile(((DocumentInput)document).toFile());
        }
        indexer.closeIndex();
    }

    @Override
    public void deserializeParameters() {
        deserializeModel();
        deserializeIndexedTerms();
    }

    @Override
    public void serializeParameters() {
        serializeModel();
        serializeIndexedTerms();
    }
    
    public SearchResult regularQuerySearch(String query, int numOfDocs){
        SearchResult result = querySearch(query, numOfDocs, DOCUMENTS_INDEX_DIR + "classified\\");
        return result;
    }
    
    public SearchResult querySearch (String query, int numOfDocs, String indexDir){  
        searcher.setIndexDir(indexDir);//ovde treba za klasifikaciju
        SearchResult searchResult = searcher.querySearch(query, numOfDocs);
        return searchResult;
    }
}