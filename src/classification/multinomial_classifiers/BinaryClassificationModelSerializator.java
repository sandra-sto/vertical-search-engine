/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.multinomial_classifiers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Sandra Stojanović
 */
public class BinaryClassificationModelSerializator implements ClassificationModelSerializator{
    
    @Override
    public void serialize(MultinomialClassificationAlgorithm model, String path) {
        try{
            tryToSerialize(model, path);
        }catch(IOException i){
            i.printStackTrace();
         }
    }
    
    private void tryToSerialize(MultinomialClassificationAlgorithm model, String path)throws IOException {
        FileOutputStream fileOut = new FileOutputStream(path + ".ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        
        out.writeObject(model);
        out.close();
        fileOut.close();
         
        System.out.println("Serialized data is saved in.ser");
    }

    @Override
    public MultinomialClassificationAlgorithm deserialize(String path) {
        MultinomialClassificationAlgorithm model = null;
        try{
           model = tryToDeserialize(path);
        }catch(IOException i){
            i.printStackTrace();
        }catch(ClassNotFoundException c){
            System.out.println("Model class not found");
            c.printStackTrace();
        }
      return model;
    } 
    
    private MultinomialClassificationAlgorithm tryToDeserialize(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(path + ".ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        
        MultinomialClassificationAlgorithm model = (MultinomialClassificationAlgorithm) in.readObject();
        in.close();
        fileIn.close();
        
        return model;
    }
}
