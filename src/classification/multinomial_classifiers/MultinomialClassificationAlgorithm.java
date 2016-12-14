/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.multinomial_classifiers;

import classification.ClassificationAlgorithm;
import classification.class_types.ClassType;
import classification.class_types.ClassificationResult;
import classification.evaluation.MultinomialAlgorithmEvaluator;
import classification.evaluation.PerformanceMeasureResult;
import java.util.List;
import classification.inputs.ClassificationInput;
import classification.regularization.Regularization;

/**
 *
 * @author Sandra Stojanović
 */
public interface MultinomialClassificationAlgorithm extends ClassificationAlgorithm {   
    void makeBinaryModels(int numberOfPredictors, ClassType[] classes);
    int getNumberOfClasses(); 
   
    void setAlgorithmEvaluator(MultinomialAlgorithmEvaluator evaluator);
    ClassificationResult classify(ClassificationInput input); 
    
    void printMacroPerformanceMeasuresResult();
    void printMicroPerformanceMeasuresResult();
    
    public PerformanceMeasureResult getMicroPerformanceMeasures();
    public PerformanceMeasureResult getMacroPerformanceMeasures();
     
    void setRegularization(Regularization regularization);
    void crossValidate(List<ClassificationInput> inputs);
    void leaveOneOut(List<ClassificationInput> inputs);
    
    void setFeatureSelector(String type);
}
