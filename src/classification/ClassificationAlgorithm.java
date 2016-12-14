/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification;

import classification.evaluation.PerformanceMeasureResult;
import classification.inputs.ClassificationInput;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Sandra Stojanović
 */
public interface ClassificationAlgorithm extends Serializable{  
    void train(List<ClassificationInput> inputs);
    void test(List<ClassificationInput> inputs); 
}
