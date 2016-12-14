/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lucene_parameters;

import java.io.IOException;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.*;

/**
 *
 * @author Sandra Stojanović
 */
public class NumberFilter extends TokenFilter{
    protected CharTermAttribute charTermAttribute =
        addAttribute(CharTermAttribute.class);
    
    protected PositionIncrementAttribute positionIncrementAttribute =
        addAttribute(PositionIncrementAttribute.class);

    public NumberFilter(TokenStream ts){
        super(ts);
    }

    @Override
    public boolean incrementToken() throws IOException {
        String nextToken = null;
        while (nextToken == null) {
            // Reached the end of the token stream being processed
            if (!this.input.incrementToken()) {
                return false;
            }
            // Get text of the current token
            String currentTokenInStream =
                this.input.getAttribute(CharTermAttribute.class)
                    .toString();
           
            if (!containsNumbers(currentTokenInStream)) {
                nextToken = currentTokenInStream;
            }
        }
        this.charTermAttribute.setEmpty().append(nextToken);
        this.positionIncrementAttribute.setPositionIncrement(1);
        return true;
    }
    
     private boolean containsNumbers(String term){
        String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for(String number : numbers)
            if(term.contains(number))
                return true;
        return false;
    }
}