package textdocuments_representations;
/**
 *
 * @author Sandra Stojanović
 */
import classification.*;
import classification.inputs.*;
import indexing.LuceneIndexer;
import java.io.IOException;
import java.nio.file.Paths;
import textdocuments_representations.terms.TermRepresentation;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.store.*;
import org.apache.lucene.util.BytesRef;
import java.util.*;
import org.apache.lucene.search.similarities.TFIDFSimilarity;

public class LuceneDocumentTfIdfRepresentationCreator implements DocumentTfIdfRepresentationCreator {   
    private String indexDir;
    DirectoryReader reader;
    TFIDFSimilarity similarity;

    public LuceneDocumentTfIdfRepresentationCreator() {
        similarity = ClassificationProcess.luceneParametersFactory.createSimilarity();
    }
   
    @Override
    public void setIndexDir(String indexDir){
        this.indexDir = indexDir;
    }
    
    @Override
    public List<ClassificationInput> createTfIdfDocumentsRepresentations() {
        List<ClassificationInput> documents = null;
        try {
            documents = tryCreateTfIdfDocumentsRepresentations();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return documents;
    }
    
    private List<ClassificationInput> tryCreateTfIdfDocumentsRepresentations() throws IOException{   
        getIdfValuesForTerms(); 
        return getTfValuesAndCreateTfIdfDocuments();     
    }
    
    private void openIndexReader() throws IOException{
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        reader = DirectoryReader.open(dir); 
    }
    
    private void closeReaders(DirectoryReader reader) throws IOException{
        reader.close(); 
        reader.directory().close(); 
        reader = null;
    }
    
    public void getIdfValuesForTerms() throws IOException{
        openIndexReader();
        BytesRef term = null;       
        Fields fields = MultiFields.getFields(reader);
        Terms terms = fields.terms(LuceneIndexer.CONTENT);
            
        TermsEnum termsEnum = terms.iterator();
     
        while((term = termsEnum.next()) != null){
            String text = term.utf8ToString();      
            float idf = similarity.idf(termsEnum.docFreq(), reader.numDocs());           
            TermRepresentation tr = new TermRepresentation(text, idf);
            TextClassificationProcess.indexedTerms.addTerm(tr);
        }
    }
   
    @Override
    public List<ClassificationInput> getTfValuesAndCreateTfIdfDocuments() throws IOException{
        List<ClassificationInput> documents = new ArrayList<>();
        if(reader == null)
            openIndexReader();        
    
        for(int docNum = 0; docNum < reader.numDocs(); docNum++){          
            Document doc = reader.document(docNum); 
            String path = doc.getField(LuceneIndexer.PATH).stringValue();
            String name = doc.getField(LuceneIndexer.NAME).stringValue();
            DocumentInput input = new DocumentInput(name, path);  
           
            setClass(doc, input);
      
            //make tfidf repreentation
            BytesRef term;
            Terms termsDoc = reader.getTermVector(docNum, LuceneIndexer.CONTENT);
            TermsEnum termsEnum = termsDoc.iterator();
            
            while((term = termsEnum.next()) != null){               
                String text = term.utf8ToString();
                float tf = termsEnum.totalTermFreq();                  
                TermRepresentation termR = TextClassificationProcess.indexedTerms.getTermRepresentation(text);
                if(termR != null) {
                    float tfidf = tf * termR.getIdf();
                    input.addFeature(new Feature(termR.getId(), tfidf));
                }
            }
            documents.add(input);
        }   
        closeReaders(reader);
        return documents;
    }
    
    private void setClass(Document doc, ClassificationInput input) {
        String className = doc.getField(LuceneIndexer.CLASS).stringValue();
        input.setClassType(ClassificationProcess.classTypes.getClassType(className));
    }
}