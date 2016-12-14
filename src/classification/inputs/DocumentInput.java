/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.inputs;

import java.io.File;

/**
 *
 * @author Sandra Stojanović
 */
public class DocumentInput extends ClassificationInput {
    String name;
    String path;
    
    public DocumentInput(String name, String path){
        super();
        this.name = name;
        this.path = path;
    }
     
    public void setName(String name) {
       this.name = name;
    }
     
    public String getName(){
        return name;
    }
    
    public void setPath(String path) {
        this.path = path;
    }  
    
    public String getPath() {
        return path;
    }
    
    public File toFile() {
       return new File(path);
    }
}