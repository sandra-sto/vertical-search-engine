package lucene_parameters;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.similarities.TFIDFSimilarity;

/**
 *
 * @author Sandra Stojanović
 */
public interface LuceneParametersFactory {
    Analyzer createAnalyzer();
    TFIDFSimilarity createSimilarity();
}