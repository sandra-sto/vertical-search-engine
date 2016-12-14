/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.binary_classifiers;

import classification.class_types.ClassType;
import classification.multinomial_classifiers.ClassificationAlgorithmType;
import classification.multinomial_classifiers.Kernel;

/**
 *
 * @author Sandra Stojanović
 */
public class BinaryClassificationAlgorithmFactory {
    
     public static BinaryClassificationAlgorithm createBinaryAlgorithm(ClassificationAlgorithmType type, int numberOfPredictors, ClassType positiveClassType, Kernel kernel){
        switch(type){
            case LOGISTIC_REGRESSION: 
                return new BinaryLogisticRegression(numberOfPredictors, positiveClassType);
            case KERNEL_LOGISTIC_REGRESSION: 
                return new KernelBinaryLogisticRegression(numberOfPredictors, positiveClassType, kernel);
            default: 
                return new BinaryLogisticRegression(numberOfPredictors, positiveClassType);
        }
    }
}