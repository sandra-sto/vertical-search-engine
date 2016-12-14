/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import classification.ClassificationProcess;
import classification.TextClassificationProcess;
import gui.SearchForm;


/**
 *
 * @author Sandra Stojanović
 */
public class MainClass {

    
    /**
     * @param args the command line arguments
     */
    public MainClass(){
        
    }
    public static void main(String[] args) {
       
        SearchForm searchForm = new SearchForm();
        searchForm.setVisible(true);        
    }  
    
    private void makeModel() {
        TextClassificationProcess classificationProcess = new TextClassificationProcess();
        classificationProcess.setModelFile("textModel");
        
        classificationProcess.createRepresentationsAndCrossValidate("D:\\data sets\\data_wiki\\data_wiki\\training_data");
        classificationProcess.createRepresentationsAndClassify("D:\\data sets\\data_wiki\\data_wiki\\test_data");
        
        classificationProcess.printDurationResults();
    }
}