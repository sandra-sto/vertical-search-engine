/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.class_types;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sandra Stojanović
 */
public class AllClassTypes {  
    static AllClassTypes instance;
    Map<String, ClassType> classTypes;

    private AllClassTypes(){
        classTypes = new HashMap<String, ClassType>();
    }
    
    public static AllClassTypes getInstance(){
        if(instance == null)
            instance = new AllClassTypes(); 
        return instance;
    }
    
    public void addClassType(ClassType classType){
        if(!classTypeExists(classType)){ 
            if(classType.getLabel() == -1)
                classType.generateLabel();
            
            classTypes.put(classType.getName(), classType);   
        }
    }
   
    private boolean classTypeExists(ClassType classType){
        return classTypes.containsKey(classType.getName());
    }
    
    public ClassType[] getClassesTypes(){
        return (ClassType[]) classTypes.values().toArray(new ClassType[classTypes.size()]);//verovatno ne radi
    }
    
    public ClassType getClassType(String name){
        return classTypes.get(name);
    }

    public void clear() {
        classTypes.clear();
    }
}