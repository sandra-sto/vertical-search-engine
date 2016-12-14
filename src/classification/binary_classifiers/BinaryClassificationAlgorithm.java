package classification.binary_classifiers;

import classification.class_types.ClassType;
import classification.ClassificationAlgorithm;
import classification.evaluation.PerformanceMeasureResult;
import classification.evaluation.BinaryAlgorithmEvaluator;
import classification.feature_selection.FeatureSelector;
import classification.inputs.ClassificationInput;
import classification.regularization.NoRegularization;
import classification.regularization.Regularization;

/**
 *
 * @author Sandra Stojanović
 */
public abstract class BinaryClassificationAlgorithm implements ClassificationAlgorithm{
    private ClassType positiveClass;
    protected Regularization regularization;
    protected BinaryAlgorithmEvaluator evaluator;
    transient protected FeatureSelector featureSelector;
  
    public BinaryClassificationAlgorithm(ClassType positiveClass){
        this.positiveClass = positiveClass;    
    }
    
    public BinaryClassificationAlgorithm(){
        regularization = new NoRegularization();
    }
  
    public void setRegularizaion(Regularization regularization){
        this.regularization = regularization;
    }
    
    public PerformanceMeasureResult getPerformanceMeasures(){
        return evaluator.getPerformanceMeasures();
    }
    
    public void setAlgorithmEvaluator(BinaryAlgorithmEvaluator evaluator){
        this.evaluator = evaluator;
    }
    
    protected int getBinaryLabel(int label){
       return (label == positiveClass.getLabel()) ? 1 : 0;
    } 
 
    public ClassType getPositiveClassType(){
        return positiveClass;
    } 
    
    public void setFeatureSelector(FeatureSelector featureSelector){
        this.featureSelector = featureSelector;
    }
    
    public abstract double classify(ClassificationInput input);
    public abstract void includeOnlySelectedFeatures();
}
