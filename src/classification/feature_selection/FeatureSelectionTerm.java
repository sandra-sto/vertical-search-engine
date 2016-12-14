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
public abstract class FeatureSelectionTerm implements Comparable<FeatureSelectionTerm>{
    int termId;
    
    public FeatureSelectionTerm(int termId){
        this.termId = termId;
    }
    
    public int getTermId() {
        return termId;
    }
    
    @Override
    public int compareTo(FeatureSelectionTerm o) {
        if(getUtilityMeasureForTerm() > o.getUtilityMeasureForTerm())
            return 1;
        else if(getUtilityMeasureForTerm() == o.getUtilityMeasureForTerm())
            return 0;
        else 
            return -1;
    }  
    
    public boolean greaterThan(FeatureSelectionTerm other){
        return compareTo(other) > 0;
    }
    public abstract float getUtilityMeasureForTerm();
}
