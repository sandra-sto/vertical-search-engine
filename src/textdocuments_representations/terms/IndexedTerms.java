/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textdocuments_representations.terms;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sandra Stojanović
 */
public class IndexedTerms implements Serializable{
    Map<String, TermRepresentation> terms;
    transient public static IndexedTerms instance;
    
    private IndexedTerms(){
        terms = new HashMap<String, TermRepresentation> ();
    }
    
    public static IndexedTerms getInstance(){
        if(instance == null)
            createInstance();
        return instance;
    }
    
    private static void createInstance(){
        instance = new IndexedTerms();
    }
    
    public void addTerm(TermRepresentation tr){
        if(!termExists(tr)){
            tr.generateId();
            terms.put(tr.getTerm(), tr);
        }
    }
   
    private boolean termExists(TermRepresentation tr){
        return terms.containsKey(tr.getTerm());
    }
    
    public TermRepresentation getTermRepresentation(String term){
        return terms.get(term);
    }
    
    public int getTermsNumber(){
        return terms.size();
    }
}