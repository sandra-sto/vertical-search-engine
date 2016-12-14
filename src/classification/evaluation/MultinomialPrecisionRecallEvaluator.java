/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.evaluation;
  
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sandra Stojanović
 */
public class MultinomialPrecisionRecallEvaluator extends MultinomialAlgorithmEvaluator{   
    float precision = 0, recall = 0, accuracy = 0;
 
    public MultinomialPrecisionRecallEvaluator(){
        super();
    }
      
    //factory method
    @Override
    public BinaryAlgorithmEvaluator createBinaryEvaluator(){
        BinaryAlgorithmEvaluator binaryEvaluator = new BinaryPrecisionRecallEvaluator();
        binaryEvaluators.add(binaryEvaluator);
        
        return binaryEvaluator;
    }
  
    private float getF1Score(float precision, float recall){
        return 2 * precision  * recall / (precision + recall);
    }
    
    @Override
    public PerformanceMeasureResult getMicroPerformanceMeasures() {
        PerformanceMeasureResult result = new PerformanceMeasureResult();  
        int truePositiveSum = 0, trueNegativeSum = 0, falsePositiveSum = 0, falseNegativeSum = 0;
        
        for(BinaryAlgorithmEvaluator binaryEvaluator : binaryEvaluators) {
            try{
                PerformanceMeasureResult binaryResult = binaryEvaluator.getPerformanceMeasures();
                
                truePositiveSum += binaryResult.getValue("true positive").intValue(); 
                trueNegativeSum += binaryResult.getValue("true negative").intValue();
                falsePositiveSum +=  binaryResult.getValue("false positive").intValue();         
                falseNegativeSum += binaryResult.getValue("false negative").intValue();          
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        
        precision = (float)truePositiveSum / (truePositiveSum + falsePositiveSum);
        recall = (float)truePositiveSum / (truePositiveSum + falseNegativeSum);
        accuracy = (float)(truePositiveSum + trueNegativeSum) / (truePositiveSum + falseNegativeSum + trueNegativeSum + falsePositiveSum);
        
        float f1Score = getF1Score(precision, recall);
        
        result.addValue("precision", precision);
        result.addValue("recall", recall);
        result.addValue("f1Score", f1Score);      
        result.addValue("accuracy", accuracy);
        
        return result;
    }
    
    @Override
    public PerformanceMeasureResult getMacroPerformanceMeasures() {
        PerformanceMeasureResult result = new PerformanceMeasureResult();  
        
        for(BinaryAlgorithmEvaluator binaryEvaluator : binaryEvaluators) {
            try{
                PerformanceMeasureResult binaryResult = binaryEvaluator.getPerformanceMeasures();
                precision += binaryResult.getValue("precision");
                recall += binaryResult.getValue("recall");
                accuracy += binaryResult.getValue("accuracy");
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        
        int size = binaryEvaluators.size();
        precision /= size;
        recall /= size;
        accuracy /= size; 
        float f1Score = getF1Score(precision, recall);
        
        result.addValue("precision", precision);
        result.addValue("recall", recall);
        result.addValue("f1Score", f1Score);      
        result.addValue("accuracy", accuracy);
        
        return result;
    }
}
