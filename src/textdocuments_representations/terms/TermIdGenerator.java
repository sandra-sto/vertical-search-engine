/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textdocuments_representations.terms;

/**
 *
 * @author Sandra Stojanović
 */
public class TermIdGenerator {
    static int termId = 0;
    
    public static int generateTermId(TermRepresentation term){
        return ++termId;
    }
}
