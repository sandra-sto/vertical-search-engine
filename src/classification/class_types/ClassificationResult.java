/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.class_types;

/**
 *
 * @author Sandra Stojanović
 */
public class ClassificationResult {
    double probability;
    ClassType resultClass;
    
    public ClassificationResult(double probability, ClassType resultClass){
        this.probability = probability;
        this.resultClass = resultClass;
    }  
     
    public double getProbability(){
        return probability;
    }  
    
    public ClassType getClassType(){
        return resultClass;
    }  
    
    public String getClassName(){
        return resultClass.getName();
    }
}