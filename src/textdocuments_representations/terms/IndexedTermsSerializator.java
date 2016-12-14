/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textdocuments_representations.terms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Sandra Stojanović
 */
public class IndexedTermsSerializator {
    
    public void serialize(IndexedTerms indexedTerms, String directory) {
        try{
            tryToSerialize(indexedTerms, directory);
        }catch(IOException i){
            i.printStackTrace();
         }
    }
    
    private void tryToSerialize(IndexedTerms indexedTerms, String file)throws IOException {
        FileOutputStream fileOut = new FileOutputStream(file + ".ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        
        out.writeObject(indexedTerms);
        out.close();
        fileOut.close();
         
        System.out.println("Serialized data is saved in.ser");
    }

    public IndexedTerms deserialize(String fileName) {
        IndexedTerms indexedTerms = null;
        try{
           indexedTerms = tryToDeserialize(fileName);
        }catch(IOException i){
            i.printStackTrace();
        }catch(ClassNotFoundException c){
            System.out.println("Model class not found");
            c.printStackTrace();
        }
      return indexedTerms;
    } 
    
    private IndexedTerms tryToDeserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(fileName + ".ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        
        IndexedTerms indexedTerms = (IndexedTerms) in.readObject();
        in.close();
        fileIn.close();
        
        return indexedTerms;
    }
}