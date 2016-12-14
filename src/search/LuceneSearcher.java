/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import classification.ClassificationProcess;
import classification.DurationResults;
import indexing.LuceneIndexer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.similarities.TFIDFSimilarity;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
/**
 *
 * @author Sandra Stojanović
 */
public class LuceneSearcher implements Searcher{
    String indexDir;
    
    Analyzer analyzer;
    TFIDFSimilarity similarity;
    IndexSearcher indexSearcher;
    
    public LuceneSearcher(){
        analyzer = ClassificationProcess.luceneParametersFactory.createAnalyzer();
        similarity = ClassificationProcess.luceneParametersFactory.createSimilarity();
    }
    
    @Override
    public SearchResult querySearch(String queryString, int numOfDocs) {      
        SearchResult searchResult = new SearchResult();
        try{
           searchResult = tryQuerySearch(queryString, numOfDocs);
        }catch(ParseException exc){
            exc.printStackTrace();
        }catch(IOException exc){
            exc.printStackTrace();
        }
        return searchResult;
    }
   
    private DirectoryReader openIndexReader() throws IOException{
        Directory dir = FSDirectory.open(Paths.get(indexDir));
	DirectoryReader reader = DirectoryReader.open(dir);
        return reader;
    }
    
    private Query parseQuery(String queryString) throws ParseException{
        QueryParser parser = new QueryParser("content", analyzer);
        Query query = parser.parse(queryString);
        return query;
    }
    
    private void createIndexSearcher(DirectoryReader reader){
        indexSearcher = new IndexSearcher(reader);
        indexSearcher.setSimilarity(similarity);
    }
    
    public SearchResult tryQuerySearch(String queryString, int numOfDocs) throws IOException, ParseException{	
        DirectoryReader reader = openIndexReader();         
	createIndexSearcher(reader);
        
        Query query = parseQuery(queryString);
        
        DurationResults durationResults = DurationResults.getInstance();     
        durationResults.setStartTime("search");    
        ScoreDoc[] scoreDocs = indexSearcher.search(query, numOfDocs).scoreDocs;
        durationResults.setEndTime();
        
        SearchResult result = makeSearchResult(scoreDocs);
        return result;
    }
    
    private SearchResult makeSearchResult(ScoreDoc[] scoreDocs) throws IOException{      
        SearchResult searchResult = new SearchResult();
        
        for(int i = 0; i < scoreDocs.length; i++){
            int documentId = scoreDocs[i].doc;
            Document doc = indexSearcher.doc(documentId);          
           
            String path = doc.getField(LuceneIndexer.PATH).stringValue();
           
            File file = new File(path);
            searchResult.addDocument(file);          
        }
        
        return searchResult;   	
    }

    @Override
    public void setIndexDir(String indexDir) {
       this.indexDir = indexDir;
    }   
}