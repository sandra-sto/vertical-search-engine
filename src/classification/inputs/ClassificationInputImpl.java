/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.inputs;

import java.util.List;

/**
 *
 * @author Sandra Stojanović
 */
public interface ClassificationInputImpl {
    public void addFeature(Feature feature);
    public List<Feature> getFeatures();
    public float getFeatureWithId(int id);
    
    public boolean containsFeature(Feature feature);
    public int getNumberOfFeatures();
    public float dotProduct(ClassificationInput ci);
}
