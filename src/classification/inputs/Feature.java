/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.inputs;

import java.io.Serializable;

/**
 *
 * @author Sandra Stojanović
 */
public class Feature implements Serializable {
    int id;
    float value;

    public Feature(){
        
    }
    
    public Feature(int id, float value){
        this.id = id;
        this.value = value;
    }
    
    public float getValue(){
        return value;
    }
    
    public void setValue(float value){
        this.value = value;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
}