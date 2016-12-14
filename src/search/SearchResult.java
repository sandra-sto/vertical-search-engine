/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sandra Stojanović
 */
public class SearchResult {
    List<File> resultDocuments;
    
    public SearchResult(){
        resultDocuments = new ArrayList<File>();
    }
    
    public SearchResult(List<File> documents){
        resultDocuments = documents;
    }
    public List<File> getResultDocuments(){
        return resultDocuments;
    }
    
    public void setResultDocuments(List<File> documents){
        resultDocuments = documents;
    }
    
    public void addDocument(File document){
        resultDocuments.add(document);
    }
    
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(File file : resultDocuments){
            builder.append(file.getName())
                  .append("\n");    
        }
        return builder.toString();
    }   
}