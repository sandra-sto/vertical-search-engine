/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_readers;

import classification.ClassificationProcess;
import classification.class_types.ClassType;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import main.MainClass;

/**
 *
 * @author Sandra Stojanović
 */
public class TextFilesDataReader implements DataReader{
    
    @Override
    public List<File> readFiles(String path, FileFilter filter){
        List<File> documents = new ArrayList<File>();
        File dir = new File(path);
        
        if(isDirectoryAndExists(dir)){
            for(File classDir : dir.listFiles()){
		if(isDirectoryAndExists(classDir)){
                    for(File document : classDir.listFiles()){
                        
                        if(filter != null && filter.accept(document))
                            documents.add(document);
                    }
                    addNewClassType(classDir.getName());
                }                    
            }        
        }
        else{
            documents.add(dir);
        }
         return documents;
    }
    
    private boolean isDirectoryAndExists(File dir){
       return dir.isDirectory() && dir.exists() && dir.canRead();
    }
    
    private void addNewClassType(String name){
        ClassificationProcess.classTypes.addClassType(new ClassType(name));
    }
}