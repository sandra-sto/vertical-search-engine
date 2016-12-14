/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.multinomial_classifiers;

import classification.multinomial_classifiers.Kernel;
import classification.inputs.ClassificationInput;

/**
 *
 * @author Sandra Stojanović
 */
public class PolynomialKernel extends Kernel{
    float c = 2;
    int degree = 3;
    
    @Override
    public float kernelFunction(ClassificationInput input1, ClassificationInput input2) {
        return polynomialKernelFunction(input1, input2);
    }
    
    private float polynomialKernelFunction(ClassificationInput input1, ClassificationInput input2){
        return (float) Math.pow(input1.dotProduct(input2) + c, degree);
    }
}