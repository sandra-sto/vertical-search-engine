/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.inputs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sandra Stojanović
 */
public class SparseClassificationInputImpl implements ClassificationInputImpl{
    Map<Integer, Feature> features;
    
    public SparseClassificationInputImpl(){
        this.features = new HashMap<Integer, Feature>();
    }
    
    @Override
    public List<Feature> getFeatures() {
        return new ArrayList(features.values());
    }
    
    @Override
    public float getFeatureWithId(int id) {
        Feature feature = features.get(id);
        return (feature != null) ? feature.getValue() : 0;
    }

    @Override
    public int getNumberOfFeatures() {
        return features.size();
    }
    
    @Override
    public boolean containsFeature(Feature feature) {
        return features.containsKey(feature.getId());
    }

    @Override
    public void addFeature(Feature feature) { 
        features.put(feature.getId(), feature);
    }

    @Override
    public float dotProduct(ClassificationInput input2) {       
        int smallerSize = Math.min(getNumberOfFeatures(), input2.getNumberOfFeatures());
        ClassificationInputImpl biggerInput, smallerInput;
                
        if(smallerSize == getNumberOfFeatures()){
            biggerInput = this;
            smallerInput = input2.getImplementation();
        }else{
            biggerInput = input2.getImplementation();
            smallerInput = this;
        }
        float dotProduct = 0,featureValue1, featureValue2;
        
        for(Feature feature : smallerInput.getFeatures()){//mora da postoje oba da proizvod ne bude 0                            
            if(biggerInput.containsFeature(feature)){
                featureValue1 = feature.getValue();
                featureValue2 = biggerInput.getFeatureWithId(feature.getId());
                dotProduct += featureValue1 * featureValue2;
            }               
        }
        return dotProduct;
    }
}