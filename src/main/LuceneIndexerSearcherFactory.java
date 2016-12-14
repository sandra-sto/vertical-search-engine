/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import textdocuments_representations.LuceneDocumentTfIdfRepresentationCreator;
import indexing.Indexer;
import indexing.LuceneIndexer;
import search.LuceneSearcher;
import search.Searcher;
import textdocuments_representations.DocumentTfIdfRepresentationCreator;

/**
 *
 * @author Sandra Stojanović
 */
public class LuceneIndexerSearcherFactory implements IndexerSearcherFactory{

    @Override
    public Indexer createIndexer() {
        return new LuceneIndexer();
    }

    @Override
    public Searcher createSearcher() {
       return new LuceneSearcher();
    }

    @Override
    public DocumentTfIdfRepresentationCreator createDocumentTfIdfRepresentationCreator() {
        return new LuceneDocumentTfIdfRepresentationCreator();
    }
}