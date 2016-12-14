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
public class MutualInformationFeatureSelectionTerm extends FeatureSelectionTerm{   
    int docs00 = 0;
    int docs01 = 0;
    int docs10 = 0;
    int docs11 = 0;
    
    public MutualInformationFeatureSelectionTerm(int termId){
      super(termId);
    }
   
    public void increaseDocs00(){
        docs00++;
    }
    
    public void increaseDocs01(){
        docs01++;
    }
     
    public void increaseDocs10(){
        docs10++;
    }
    
    public void increaseDocs11(){
        docs11++;
    }
    
    private float log2(float x){
        return (float) (Math.log10(x) / Math.log10(2));
    }
    
    @Override
    public float getUtilityMeasureForTerm(){
        int docs1x = docs10 + docs11;
        int docsx1 = docs01 + docs11;
        int docs0x = docs00 + docs01;
        int docsx0 = docs00 + docs10;
        int totalDocs = docs1x + docs0x;
        
        return (1/totalDocs) *(docs11 * log2(totalDocs * docs11/ docs1x * docsx1) + 
                docs01 * log2(totalDocs * docs01/ docs0x * docsx1) + 
                docs10 * log2(totalDocs * docs10/ docs1x * docsx0) +
                docs00 * log2(totalDocs * docs00/ docs0x * docsx0));
    }
}