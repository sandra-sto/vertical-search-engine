package classification.binary_classifiers;

import classification.multinomial_classifiers.Kernel;
import classification.class_types.ClassType;
import java.util.List;
import classification.inputs.ClassificationInput;

public class KernelBinaryLogisticRegression extends BinaryLogisticRegression{    
    Kernel kernel;
    public KernelBinaryLogisticRegression(int numberOfPredictors, Kernel kernel){
        super(numberOfPredictors);
        this.kernel = kernel; 
    }
    
    public KernelBinaryLogisticRegression(int numberOfPredictors, ClassType positiveClass, Kernel kernel){
        super(numberOfPredictors, positiveClass);
        this.kernel = kernel; 
    }   
    
    @Override
    public double classify(ClassificationInput input) {
        double linearCombination = weights[0];       
        for(int j = 0; j < weights.length - 1; j++) {  
            if(selectedFeaturesIds == null || selectedFeaturesIds.contains(j))
                linearCombination += weights[j+1] * kernel.kernelFunction(input, kernel.getDocument(j));         
	}  
	double probability = sigmoid(linearCombination);      
        return probability;
    }
  
    private void setNumberOfWeights(int n){
        weights = new double[n];
    }
   
    @Override
    public void train(List<ClassificationInput> inputs) {
        int inputsSize = inputs.size();
    
        setNumberOfWeights(inputsSize + 1);
        int n = 0, index; 
           
        while(n++ < numOfIterations){ 
            for (int i = 0; i < inputsSize; i++) { 				
                ClassificationInput input = inputs.get(i);         
                int label = getBinaryLabel(input.getClassLabel());  
                double predicted = classify(input);
                
                weights[0] -= (predicted - label);
                
                for(int j = 0; j < inputsSize; j++){                   
                    if(i > j)
                        index = j * inputsSize - (j - 1) * j >> 1 + i - j; 
                    else
                        index = i * inputsSize - (i - 1) * i >> 1 + i - j;
                    
                    if(featureSelector != null && n == 1 && label == 1){
                        featureSelector.updateTermStatistic(index);
                    }
                    if(selectedFeaturesIds == null || selectedFeaturesIds.contains(index)){
                        float value = kernel.getKernelValue(index);
                        partialDerivativeOfCostFunction = (predicted - label) * value;

                        weights[j+1] -= rate * (partialDerivativeOfCostFunction + regularization.getRegularizationPartialDerivative(weights[j]));//po tom teta parametru 
                    }
                }
            }
            if(featureSelector != null && n == 1)
                includeOnlySelectedFeatures();
        }
    }
}