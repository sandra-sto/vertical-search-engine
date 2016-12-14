/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.feature_selection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sandra Stojanović
 */
public class FrequencyBasedFeatureSelector extends FeatureSelector{ 
    Map<Integer, FrequencyBasedFeatureSelectionTerm> termsStatistics = new HashMap<Integer, FrequencyBasedFeatureSelectionTerm>();
    
    public void updateTermStatistic(int termId){
        increaseNumOfDocs(termId);  
    }
    
    private boolean termExists(int termId){
        return termsStatistics.containsKey(termId);
    }
    
    private FrequencyBasedFeatureSelectionTerm getFeatureSelectionResult(int termId){
        if(termExists(termId)){
            return termsStatistics.get(termId);
        }else{
            FrequencyBasedFeatureSelectionTerm selectionResult = new FrequencyBasedFeatureSelectionTerm(termId);
            termsStatistics.put(termId, selectionResult);
            return selectionResult;
        }
    }

    private void increaseNumOfDocs(int termId) {
        getFeatureSelectionResult(termId).increaseNumberOfDocs();
    }

    @Override
    public List<FeatureSelectionTerm> getTerms() {
        return new ArrayList<>(termsStatistics.values());
    }
}