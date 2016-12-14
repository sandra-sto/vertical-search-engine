/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.inputs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sandra Stojanović
 */
public class DenseClassificationInputImpl implements ClassificationInputImpl{
    List<Feature> features;

    public DenseClassificationInputImpl(){
        features = new ArrayList();
    }
  
    @Override
    public void addFeature(Feature feature) {
        features.add(feature);
    }

    @Override
    public List<Feature> getFeatures() {
        return features;
    }

    @Override
    public float getFeatureWithId(int id) {
       return features.get(id).getValue();
    }

    @Override
    public boolean containsFeature(Feature feature) {
        return features.contains(feature);
    }

    @Override
    public int getNumberOfFeatures() {
        return features.size();
    }

    @Override
    public float dotProduct(ClassificationInput ci) {
        float dotProduct = 0,featureValue1, featureValue2;
        for(Feature feature : features){//mora da postoje oba da proizvod ne bude 0                            
                featureValue1 = feature.getValue();
                featureValue2 = ci.getFeatureWithId(feature.getId());
                dotProduct += featureValue1 * featureValue2;
        }               
        return dotProduct;
    }
}