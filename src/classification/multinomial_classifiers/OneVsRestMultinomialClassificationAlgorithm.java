
package classification.multinomial_classifiers;

import classification.binary_classifiers.BinaryClassificationAlgorithm;
import classification.binary_classifiers.BinaryClassificationAlgorithmFactory;
import classification.class_types.ClassType;
import classification.class_types.ClassificationResult;
import classification.evaluation.MultinomialAlgorithmEvaluator;
import classification.evaluation.PerformanceMeasureResult;
import classification.evaluation.BinaryAlgorithmEvaluator;
import classification.feature_selection.FeatureSelectorFactory;
import java.util.List;
import classification.inputs.ClassificationInput;
import classification.regularization.Regularization;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 *
 * @author Sandra Stojanović
 */
public class OneVsRestMultinomialClassificationAlgorithm implements MultinomialClassificationAlgorithm{
    MultinomialAlgorithmEvaluator evaluator;
    BinaryClassificationAlgorithm binaryModels[];
    ClassType[] classes;
    
    ClassificationAlgorithmType type;
    public Kernel kernel;
    
    public OneVsRestMultinomialClassificationAlgorithm(ClassificationAlgorithmType type){
        super();           
        this.type = type;   
    }
    
    public OneVsRestMultinomialClassificationAlgorithm(ClassificationAlgorithmType type, Kernel kernel){
        super();           
        this.type = type; 
        this.kernel = kernel;
    }
    
    public void makeBinaryModels(int numberOfPredictors, ClassType[] classes){
        this.classes = classes;
        int numberOfClasses = getNumberOfClasses();
        
        this.binaryModels = new BinaryClassificationAlgorithm[numberOfClasses];
        
        for(int i = 0; i < numberOfClasses; i++){
           binaryModels[i] = BinaryClassificationAlgorithmFactory.createBinaryAlgorithm(type, numberOfPredictors, classes[i], kernel);
        }  
    }
   
    @Override
    public int getNumberOfClasses(){
        return classes.length; 
    }  
    
    @Override
    public ClassificationResult classify(ClassificationInput input) {
        double maxProbability = 0, probability; 
        ClassType classType = null;
        
        for(BinaryClassificationAlgorithm binaryModel : binaryModels){
            probability = binaryModel.classify(input);
            
            if(probability > maxProbability){
                maxProbability = probability;
                classType = binaryModel.getPositiveClassType();
            }
        }
        return new ClassificationResult(maxProbability, classType);      
//        SimpleEntry<Double, BinaryClassificationAlgorithm> maxClass = 
//                Arrays.stream(binaryModels).parallel()
//                .map(x -> new SimpleEntry<>(x.classify(input), x))
//                .max(Comparator.comparing(x -> x.getKey())).get();
//        
//        return new ClassificationResult(maxClass.getKey(), maxClass.getValue().getPositiveClassType());
    }
 
    @Override
    public void train(List<ClassificationInput> inputs) {
        if(kernel != null)
            kernel.buildKernelMatrix(inputs);
        
       // Arrays.stream(binaryModels).parallel().forEach( x -> ((BinaryClassificationAlgorithm)x).train(inputs));
        
        for(BinaryClassificationAlgorithm binaryModel : binaryModels){
            binaryModel.train(inputs);           
        }
    }
  
    @Override
    public void test(List<ClassificationInput> inputs) {   
        Arrays.stream(binaryModels).parallel().forEach( x -> ((BinaryClassificationAlgorithm)x).test(inputs));   
    }
       
    @Override
    public PerformanceMeasureResult getMicroPerformanceMeasures(){
        return evaluator.getMicroPerformanceMeasures(); 
    }
    
    @Override
    public PerformanceMeasureResult getMacroPerformanceMeasures(){
        return evaluator.getMacroPerformanceMeasures(); 
    }
    
    @Override
    public void printMicroPerformanceMeasuresResult(){
        System.out.println(getMicroPerformanceMeasures().toString());
    }
    
    @Override
    public void printMacroPerformanceMeasuresResult(){
        System.out.println(getMicroPerformanceMeasures().toString());
    }
    
    public void setAlgorithmEvaluator(MultinomialAlgorithmEvaluator evaluator){
        this.evaluator = evaluator;
        for(BinaryClassificationAlgorithm model : binaryModels){          
            BinaryAlgorithmEvaluator binaryEvaluator = evaluator.createBinaryEvaluator();
            model.setAlgorithmEvaluator(binaryEvaluator);
        }
    }

    public void setFeatureSelector(String type) {
        for(BinaryClassificationAlgorithm model : binaryModels){          
            model.setFeatureSelector(FeatureSelectorFactory.makeFeatureSelector(type));
        }
    }

    @Override
    public void setRegularization(Regularization regularization) {
        for(BinaryClassificationAlgorithm model : binaryModels){          
            model.setRegularizaion(regularization);
        }
    }
    
    public void crossValidate(List<ClassificationInput> inputs){//svi dokumenti
        Collection<List<ClassificationInput>> docs=inputs.stream().collect(Collectors.groupingBy(input -> input.getClassLabel())).values();
 
        List<ClassificationInput> trainDocs;
        List<ClassificationInput> testDocs;
        
        int partitions = 10;
        
        for(int i = 0; i < partitions; i++){
            trainDocs = new ArrayList();
            testDocs = new ArrayList();
            
            for(List<ClassificationInput> classDocs : docs){
                int size = classDocs.size();
                int oneTenth = size / 10;
                
                testDocs.addAll(classDocs.subList(i*oneTenth, (i+1)*oneTenth));
                trainDocs.addAll(classDocs.subList(0, i*oneTenth));
                trainDocs.addAll(classDocs.subList((i+1)*oneTenth, size));
            }
            
            train(trainDocs);
            test(testDocs);
        }
    }
    
    public void leaveOneOut(List<ClassificationInput> inputs){
        int size = inputs.size();
        
        List<ClassificationInput> trainDocs;
        List<ClassificationInput> testDocs;
        
        for(int i = 0; i < size; i++){//iterations
            trainDocs = new ArrayList();
            testDocs = new ArrayList();
            
            testDocs.add(inputs.get(i));
            trainDocs.addAll(inputs.subList(0, i));
            trainDocs.addAll(inputs.subList(i + 1, size));
            
            train(trainDocs);
            test(testDocs);
        }
    }
}