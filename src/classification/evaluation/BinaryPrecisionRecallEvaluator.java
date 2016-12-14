/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.evaluation;

/**
 *
 * @author Sandra Stojanović
 */
public class BinaryPrecisionRecallEvaluator implements BinaryAlgorithmEvaluator{
    int truePositives = 0;
    int trueNegatives = 0;
    int falseNegatives = 0;
    int falsePositives = 0;
    
    @Override
    public void evaluateLabel(int predictedLabel, int trueLabel){//ovo se ni ne zove
        if(predictedLabel == 1){
            if(trueLabel == 1)
                truePositives++;//tl = 1, pl = 1 
            else
                falsePositives++;//tl = 0, pl = 1
        }
        else{//0
            if(trueLabel == 1)
                falseNegatives++; //tl = 1, pl = 0
            else
                trueNegatives++;//tl = 0, pl = 0         
        }
    }
  
    private float getPrecision(){
        if (truePositives == 0)
            return 0;
        return (float) truePositives / (truePositives + falsePositives);
    } 
    
    private float getRecall(){
        if (truePositives == 0)
            return 0;
        return (float) truePositives / (truePositives + falseNegatives);
    }
    
    private float getAccuracy(){
        int correctClassified = truePositives + trueNegatives;
        int incorrectClassified = falsePositives + falseNegatives; 
        
        return (float) correctClassified / (correctClassified + incorrectClassified);
    }
    
    @Override
    public PerformanceMeasureResult getPerformanceMeasures() {
        PerformanceMeasureResult result = new PerformanceMeasureResult();
       
        result.addValue("precision", getPrecision());
        result.addValue("recall", getRecall());
        result.addValue("accuracy", getAccuracy());
        result.addValue("true positive", (float)truePositives);
        result.addValue("true negative", (float)trueNegatives);
        result.addValue("false positive", (float)falsePositives);
        result.addValue("false negative", (float)falseNegatives);
        return result;
    }   
}