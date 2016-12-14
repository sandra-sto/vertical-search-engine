package indexing;

/**
 *
 * @author Sandra Stojanović
 */
import data_readers.TextFilesDataReader;
import classification.ClassificationProcess;
import classification.class_types.ClassType;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.List;
import indexing.file_filters.AllFilesFilter;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.similarities.TFIDFSimilarity;
import org.apache.lucene.store.FSDirectory;
import data_readers.DataReader;

public class LuceneIndexer implements Indexer {
    public final static String CONTENT = "content";
    public final static String PATH = "path";
    public final static String NAME = "name";
    public final static String CLASS = "class";  
    
    private String indexDir;   
    private String filesToIndex;	
    private List<File> files;
    
    private Analyzer analyzer;
    private TFIDFSimilarity similarity;
    private IndexWriter writer;
       
    public LuceneIndexer(){
	this.similarity = ClassificationProcess.luceneParametersFactory.createSimilarity();
	this.analyzer = ClassificationProcess.luceneParametersFactory.createAnalyzer();
    }
  
    private FileFilter createFileFilter(){
        return new AllFilesFilter();
    }
    
    private IndexWriter createIndexWriter(){ 
        IndexWriter writer = null;
        try{
            FSDirectory indexDirectory = FSDirectory.open(Paths.get(indexDir));
			
            IndexWriterConfig config = new IndexWriterConfig(analyzer);		
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            config.setSimilarity(similarity);
			
            writer = new IndexWriter(indexDirectory,config);		
        }
        catch(IOException ex){
            System.out.println( "Cannot create writer " + ex.getMessage());
        }		
        return writer;
    }
	
    private FieldType createContentFieldType(){
        FieldType ftContent = new FieldType();
        
        ftContent.setTokenized(true);
        ftContent.setStoreTermVectors(true);
        ftContent.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
	
        return ftContent;
    }
    
    private FieldType createStoreAndNonTokenizedFieldType(){
        FieldType fieldType = new FieldType();
        
        fieldType.setStored(true);
        fieldType.setTokenized(false);
        
        return fieldType;
    } 
   
    @Override
    public void indexFile(File file){
        try{
            indexFileWithFields(file);
        }catch(IOException ex){
		System.out.println(ex.getMessage());
        }	          
    }
     
    private void indexFileWithFields(File file) throws IOException{
        Document doc = new Document();
        Reader fileReader = new FileReader(file);
            
        FieldType ftContent = createContentFieldType();
        doc.add(new Field(CONTENT, fileReader, ftContent));
							
        FieldType ftPath = createStoreAndNonTokenizedFieldType();
        doc.add(new Field(PATH, file.getPath(), ftPath));
            
        FieldType ftName = createStoreAndNonTokenizedFieldType();
        doc.add(new Field(NAME, file.getName(), ftPath));
        
        FieldType ftClass = createStoreAndNonTokenizedFieldType();
        String className = file.getParentFile().getName();
        doc.add(new Field(CLASS, className, ftClass));

        writer.addDocument(doc);
    }
    
    @Override
    public void indexFiles(){	
 	try{	                          
            tryIndexFiles();        	
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private void tryIndexFiles() throws IOException{
        addFilesForIndexing(); 
        files.parallelStream().forEach(x -> indexFile(x));

        closeIndex();
    }
    
    @Override
    public void closeIndex() throws IOException{
        writer.close();
    }
    
    @Override
    public void addFilesForIndexing(){
        DataReader filesReader = new TextFilesDataReader();
        
        FileFilter fileFilter = createFileFilter();
        files = filesReader.readFiles(filesToIndex, fileFilter);
    }

    private void addNewClassType(String className){
        ClassificationProcess.classTypes.addClassType(new ClassType(className));
    }
   
    @Override
    public void setFilesToIndex(String filesToIndex) {
        this.filesToIndex = filesToIndex;
    }

    @Override
    public void setIndexDir(String indexDir) {
       this.indexDir = indexDir;
       writer = createIndexWriter();
    }

    @Override
    public String getIndexDir() {
        return indexDir;
    }
}