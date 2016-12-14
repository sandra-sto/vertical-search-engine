/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textdocuments_representations.terms;

import java.io.Serializable;

/**
 *
 * @author Sandra Stojanović
 */
public class TermRepresentation implements Serializable{ 
    int id;
    String term;
    float idf;
    
    public TermRepresentation(String term, float idf){
        this.term = term;
        this.idf = idf;
    } 
    
    public void generateId(){
        id = TermIdGenerator.generateTermId(this);      
    }
    
    public int getId(){
        return id;
    }
    
    public String getTerm(){
       return term;
    }
    
    public float getIdf(){
       return idf;
    }
    
    public void setIdf(float idf){
        this.idf = idf;
    }
}