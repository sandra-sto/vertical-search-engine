/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.class_types;

import java.io.Serializable;

/**
 *
 * @author Sandra Stojanović
 */
public class ClassType implements Serializable{   
    private int label;
    private String name;
   
    public ClassType (String name){
        this.name = name;
        label = -1;
    }
    
    public void generateLabel(){    
        label = ClassLabelGenerator.generateClassLabel();      
    }
 
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
    
    public void setLabel(int label){
        this.label = label;
    }
    
    public int getLabel(){
        return label;
    }
}