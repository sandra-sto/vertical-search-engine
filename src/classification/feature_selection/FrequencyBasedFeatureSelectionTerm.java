/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.feature_selection;

/**
 *
 * @author Sandra Stojanović
 */
public class FrequencyBasedFeatureSelectionTerm extends FeatureSelectionTerm{
    int numberOfDocs = 0;
    
    public FrequencyBasedFeatureSelectionTerm(int termId){
        super(termId);
    }
    
    public void increaseNumberOfDocs(){
        numberOfDocs++;
    }

    private int getNumberOfDocs() {
        return numberOfDocs;
    }
    
    @Override
    public float getUtilityMeasureForTerm(){
        return getNumberOfDocs();
    }
}