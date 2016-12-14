/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.inputs;

import classification.class_types.ClassType;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Sandra Stojanović
 */
public class ClassificationInput implements Serializable{   
    ClassificationInputImpl impl;
    ClassType classType;
    int id;
    
    public ClassificationInput(){
        this.impl = new SparseClassificationInputImpl();
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public ClassType getClassType(){
        return classType;
    }
    public void setClassType(ClassType classType){
        this.classType = classType;
    }
    
    public int getClassLabel(){
        return classType.getLabel();
    }
    
    public String getClassName(){
        return classType.getName();
    }
    
    public void addFeature(Feature feature){
        impl.addFeature(feature);
    }
    
    public List<Feature> getFeatures(){
        return impl.getFeatures();
    }
    
    public float getFeatureWithId(int id){
        return impl.getFeatureWithId(id);
    }
    
    public boolean containsFeature(Feature feature){
        return impl.containsFeature(feature);
    }
    
    public int getNumberOfFeatures(){
        return impl.getNumberOfFeatures();
    }
    
    public float dotProduct(ClassificationInput ci){
        return impl.dotProduct(ci);
    }
    
    public void setImplementation(ClassificationInputImpl impl){
        this.impl = impl;
    }
    
    public ClassificationInputImpl getImplementation(){
        return impl;
    }
}