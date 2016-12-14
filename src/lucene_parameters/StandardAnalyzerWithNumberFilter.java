/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lucene_parameters;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import static org.apache.lucene.analysis.standard.StandardAnalyzer.STOP_WORDS_SET;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

/**
 *
 * @author Sandra Stojanović
 */
public class StandardAnalyzerWithNumberFilter extends Analyzer{

    @Override
    protected TokenStreamComponents createComponents(String string) {
        Tokenizer tokenizer = new StandardTokenizer();
        TokenStream filter = new StandardFilter(tokenizer);
        filter = new LowerCaseFilter(filter);
        filter = new StopFilter(filter, STOP_WORDS_SET);
        filter = new NumberFilter(filter);
        
            
        return new TokenStreamComponents(tokenizer, filter);
    }
}