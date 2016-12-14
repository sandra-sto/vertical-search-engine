/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexing;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author Sandra Stojanović
 */
public interface Indexer {
    void indexFiles();   
    void indexFile(File file);
    
    void addFilesForIndexing();
    void setFilesToIndex(String filesToIndex);
    
    void setIndexDir(String indexDir);
    String getIndexDir();
    
    void closeIndex() throws IOException;
}