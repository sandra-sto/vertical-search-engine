/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.evaluation;

import java.io.Serializable;

/**
 *
 * @author Sandra Stojanović
 */
public interface BinaryAlgorithmEvaluator extends Serializable{
    void evaluateLabel(int predictedLabel, int realLabel); 
    PerformanceMeasureResult getPerformanceMeasures();
}