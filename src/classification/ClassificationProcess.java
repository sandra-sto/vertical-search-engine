package classification;

import classification.class_types.*;
import classification.inputs.*;
import classification.multinomial_classifiers.*;
import java.util.*;
import lucene_parameters.LuceneParametersFactory;
import lucene_parameters.LuceneParametersFactoryImpl;

/**
 *
 * @author Sandra Stojanović
 */
public abstract class ClassificationProcess {   
    protected MultinomialClassificationAlgorithm algorithm;  
    protected ClassificationModelSerializator modelSerializator;
   
    protected DurationResults durationResults;
    //static
    public static AllClassTypes classTypes;
    public static LuceneParametersFactory luceneParametersFactory;
    
    //static final
    private String modelFile = "classificationModels\\models\\";
    
    public ClassificationProcess(){
        classTypes = AllClassTypes.getInstance(); 
        luceneParametersFactory = LuceneParametersFactoryImpl.getInstance();

        modelSerializator = new BinaryClassificationModelSerializator();
       
        durationResults = DurationResults.getInstance();
        algorithm = new OneVsRestMultinomialClassificationAlgorithm(ClassificationAlgorithmType.LOGISTIC_REGRESSION);     
    }
   
    protected void serializeModel(){
        if(algorithm != null)
            modelSerializator.serialize(algorithm, modelFile);
    }
    
    public void deserializeModel(){
        algorithm = modelSerializator.deserialize(modelFile);
    }
    
    public void createRepresentationsAndTrain(String trainPath){
        List<ClassificationInput> instances = createTrainingRepresentation(trainPath);
        durationResults.setStartTime("training");
        train(instances);
        durationResults.setEndTime();
    }
    
    public void train(List<ClassificationInput> inputs){  
        initiateClassificationAlgorithm();
        algorithm.train(inputs);
        serializeParameters();     
    }
    
    public void createRepresentationsAndCrossValidate(String path){
        List<ClassificationInput> instances = createTrainingRepresentation(path);
        durationResults.setStartTime("cross validation");
        crossValidate(instances);
        durationResults.setEndTime();
        algorithm.printMicroPerformanceMeasuresResult();   
        serializeParameters();
    }
    
    public void createRepresentationsAndLeaveOneOutValidate(String path){
        List<ClassificationInput> instances = createTrainingRepresentation(path);
        durationResults.setStartTime("leave one out validation");
        leaveOneOut(instances);
        durationResults.setEndTime();
        algorithm.printMicroPerformanceMeasuresResult();   
        serializeParameters();
    }
    
    public void leaveOneOut(List<ClassificationInput> documents){
        initiateClassificationAlgorithm();
        algorithm.leaveOneOut((List<ClassificationInput>)documents);
    }
     
    public void crossValidate(List<ClassificationInput> documents){
        initiateClassificationAlgorithm();
        algorithm.crossValidate((List<ClassificationInput>)documents);
    }
    
    public void setModelFile(String name){
        modelFile += name;
    }
    
    public void createRepresentationsAndTest(String testPath){  
        deserializeParameters();
   
        List<ClassificationInput> instances = createTestRepresentation(testPath); 
        test(instances);    
    }
    
    public void test(List<ClassificationInput> documents) {
        durationResults.setStartTime("test wihout indexing");
        algorithm.test(documents);     
        durationResults.setEndTime();
        algorithm.printMicroPerformanceMeasuresResult();   
    }
     
    public ClassificationResult classify(ClassificationInput input){
        durationResults.setStartTime("classification one");
        ClassificationResult result = algorithm.classify(input);
        durationResults.setEndTime();
        return result;
    }
    
    public void printDurationResults(){
        durationResults.printDurationResults();
    }
    
    public DurationResults getDurationResults(){
        return durationResults;
    }
    
    public abstract void deserializeParameters();
    public abstract void serializeParameters();
    public abstract void initiateClassificationAlgorithm();
    public abstract List<ClassificationInput> createTrainingRepresentation(String path);
    public abstract List<ClassificationInput> createTestRepresentation(String path);
    public abstract List<ClassificationInput> createClassificationRepresentation(String path);   
}