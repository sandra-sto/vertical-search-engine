/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

/**
 *
 * @author Sandra Stojanović
 */
public interface Searcher {
    SearchResult querySearch(String query, int numOfDocs);
    void setIndexDir(String indexDir);
}
