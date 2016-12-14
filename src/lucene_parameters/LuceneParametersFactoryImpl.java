package lucene_parameters;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.TFIDFSimilarity;

/**
 *
 * @author Sandra Stojanović
 */
public class LuceneParametersFactoryImpl implements LuceneParametersFactory{

    static private LuceneParametersFactoryImpl instance;
    
    private LuceneParametersFactoryImpl(){
        
    }
    
    public static LuceneParametersFactoryImpl getInstance(){
        if(instance == null)
            instance = new LuceneParametersFactoryImpl();
        return instance;
    }
    
    @Override
    public Analyzer createAnalyzer() {
        return new StandardAnalyzer();
    }

    @Override
    public TFIDFSimilarity createSimilarity() {
        return new ClassicSimilarity();
    }
}