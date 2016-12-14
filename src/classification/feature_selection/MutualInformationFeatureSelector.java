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

public class MutualInformationFeatureSelector extends FeatureSelector {  
    Map<Integer, MutualInformationFeatureSelectionTerm> termsStatistics = new HashMap<Integer, MutualInformationFeatureSelectionTerm>();
    
    public void updateTermStatistic(int termId, int documentClassLabel, float featureValue){//ako sadrzi termin
        if(featureValue != 0){
            if(documentClassLabel == 0)
                increase10ForTerm(termId);
            else
                increase11ForTerm(termId);
        }else{
            if(documentClassLabel == 0)
                increase00ForTerm(termId);
            else
                increase01ForTerm(termId);
        }
    }
    
    private boolean termExists(int termId){
        return termsStatistics.containsKey(termId);
    }
    
    private MutualInformationFeatureSelectionTerm getFeatureSelectionResult(int termId){
        if(termExists(termId)){
            return termsStatistics.get(termId);
        }else{
            MutualInformationFeatureSelectionTerm selectionResult = new MutualInformationFeatureSelectionTerm(termId);
            termsStatistics.put(termId, selectionResult);
            return selectionResult;
        }
    }
    
    private void increase00ForTerm(int termId){
        getFeatureSelectionResult(termId).increaseDocs00();
    }
    
    private void increase01ForTerm(int termId){
        getFeatureSelectionResult(termId).increaseDocs01(); 
    }
    
    private void increase10ForTerm(int termId){
        getFeatureSelectionResult(termId).increaseDocs10();
    }
    
    private void increase11ForTerm(int termId){
        getFeatureSelectionResult(termId).increaseDocs11();
    }  
    
    @Override
    public void updateTermStatistic(int termId) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<FeatureSelectionTerm> getTerms() {
        return new ArrayList<FeatureSelectionTerm>(termsStatistics.values());
    }
}