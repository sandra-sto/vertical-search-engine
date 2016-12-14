/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.feature_selection;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Sandra Stojanović
 */
public abstract class FeatureSelector {
    int k = 1500;  
    
    public Set<Integer> getFirstKTerms(){
        FeaturesMaxHeap heap = new FeaturesMaxHeap();
        Set<Integer> topTermsIds = new HashSet<>();
       
        List<FeatureSelectionTerm> results = getTerms();
        heap.insertAll(results);

        for(int i = 0; i < k; i++){
            if(i == results.size())
                break;

            FeatureSelectionTerm term = heap.remove();
            topTermsIds.add(term.getTermId());
        }
        return topTermsIds;
    }
    
    public abstract void updateTermStatistic(int termId);
    public abstract List<FeatureSelectionTerm> getTerms();
}