package classification.binary_classifiers;

import classification.class_types.ClassType;
import java.util.List;
import classification.inputs.ClassificationInput;
import classification.inputs.Feature;
import java.util.Set;
import java.util.Collections;
/**
 *
 * @author Sandra Stojanović
 */
public class BinaryLogisticRegression extends BinaryClassificationAlgorithm{
    protected double weights[];
    
    protected final int numOfIterations = 10;
    private double threshold = 0.5;   
    protected double maxValueForCostFunctionPartialDer = 0.00000000015;
    double partialDerivativeOfCostFunction = 1;
  
    float rate = 0.001f;
   
    Set<Integer> selectedFeaturesIds;
    
    public BinaryLogisticRegression(int numberOfPredictors){
        super();
        weights = new double[numberOfPredictors + 1];
    }
    
    public BinaryLogisticRegression(int numberOfPredictors, ClassType positiveClass){
        super(positiveClass);
        weights = new double[numberOfPredictors + 1];
    }
 
    protected double sigmoid(double value) {
        return 1. / (1. + Math.exp(- value));
    }
        
    @Override
    public double classify(ClassificationInput input) {
        List<Feature> features = input.getFeatures();   
        double linearCombination = weights[0];
        
        for(Feature feature : features) {
            int featureId = feature.getId();
            if(selectedFeaturesIds == null || selectedFeaturesIds.contains(featureId)){
                float featureValue = feature.getValue();        
                linearCombination += weights[featureId] * featureValue;
            }
	}  
	double probability = sigmoid(linearCombination);      
        return probability;
    }
    
    @Override
    public void test(List<ClassificationInput> inputs){
        for(ClassificationInput input : inputs){
            double probability = classify(input);

            int predictedLabel = getClassResponse(probability);
            int realLabel  = getBinaryLabel(input.getClassLabel());

            evaluator.evaluateLabel(predictedLabel, realLabel); 
        }
    }
    
    private int getClassResponse(double probability){
      return (int) Math.ceil(probability - threshold);
    }
  
    @Override
    public void train(List<ClassificationInput> inputs) {       
        int inputsSize = inputs.size();
        Collections.shuffle(inputs);
        
        int iteration = 0;  
        
        while (iteration++ < numOfIterations){   
            oneIteration(iteration, inputsSize, inputs);
            
            if(featureSelector != null && iteration == 1){
                includeOnlySelectedFeatures(); 
            }
        }
    }

    private void oneIteration(int iteration, int inputsSize, List<ClassificationInput> inputs) {
        for (int i = 0; i < inputsSize; i++) { 				
            ClassificationInput trainingInput = inputs.get(i); 
            
            int label = getBinaryLabel(trainingInput.getClassLabel());               
            double predicted = classify(trainingInput);
            weights[0] -= rate * (predicted - label);//first weight is not regularized
            
            List<Feature> features = trainingInput.getFeatures();
               
            for(Feature feature : features){
                float featureValue = feature.getValue();
                int featureId = feature.getId();                 

                if(featureSelector != null && iteration == 1 && label == 1){
                    featureSelector.updateTermStatistic(featureId);
                }

                if(selectedFeaturesIds == null || selectedFeaturesIds.contains(featureId)){       
                    partialDerivativeOfCostFunction = (predicted - label) * featureValue;//value

                    weights[featureId] -= rate * (partialDerivativeOfCostFunction + 
                        regularization.getRegularizationPartialDerivative(weights[featureId]));  
                }	
            }
        }
    }
    
    public void includeOnlySelectedFeatures(){  
        this.selectedFeaturesIds = featureSelector.getFirstKTerms();
        
        for(int id = 0; id < weights.length; id++){
            if(!selectedFeaturesIds.contains(id))
                weights[id] = 0;
        }
    }
}