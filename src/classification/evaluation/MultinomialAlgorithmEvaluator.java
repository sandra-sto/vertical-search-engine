/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.evaluation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sandra Stojanović
 */
public abstract class MultinomialAlgorithmEvaluator implements Serializable{
    protected List<BinaryAlgorithmEvaluator> binaryEvaluators;
    
    public MultinomialAlgorithmEvaluator(){
        binaryEvaluators = new ArrayList<BinaryAlgorithmEvaluator>();
    }
    
    public abstract BinaryAlgorithmEvaluator createBinaryEvaluator();
    public abstract PerformanceMeasureResult getMacroPerformanceMeasures();
    public abstract PerformanceMeasureResult getMicroPerformanceMeasures();
}
