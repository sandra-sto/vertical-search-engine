/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.multinomial_classifiers;

import classification.inputs.ClassificationInput;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Sandra Stojanović
 */
public abstract class Kernel implements Serializable{
    List<ClassificationInput> trainingSet; 
    float[] kernelMatrix;
   
    public Kernel(){
      
    }   
   
    public ClassificationInput getDocument(int j){
        if(trainingSet != null)
            return trainingSet.get(j);
        else return null;
    }
    
    public void buildKernelMatrix(List<ClassificationInput> inputs){
        if(kernelMatrix == null){
            this.trainingSet = inputs;
            int inputsSize = inputs.size();

            kernelMatrix = new float[inputsSize * (inputsSize + 1) >> 1];
            for(int i = 0, kernelMatrixCounter = 0; i < inputsSize; i++){
                ClassificationInput input1 = inputs.get(i);
                
                for(int j = i; j < inputsSize; j++){
                    ClassificationInput input2 = inputs.get(j);
                    kernelMatrix[kernelMatrixCounter++] = kernelFunction(input1, input2);
                }
            }
        }
    }
    
    public float getKernelValue(int index){
        return kernelMatrix[index];
    }
    
    public abstract float kernelFunction(ClassificationInput input1, ClassificationInput input2);
}