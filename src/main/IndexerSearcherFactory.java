/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import indexing.Indexer;
import search.Searcher;
import textdocuments_representations.DocumentTfIdfRepresentationCreator;

/**
 *
 * @author Sandra Stojanović
 */
public interface IndexerSearcherFactory {
    Indexer createIndexer();
    Searcher createSearcher();
    DocumentTfIdfRepresentationCreator createDocumentTfIdfRepresentationCreator();
}
