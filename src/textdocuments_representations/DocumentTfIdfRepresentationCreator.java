/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textdocuments_representations;

import classification.inputs.ClassificationInput;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Sandra Stojanović
 */
public interface DocumentTfIdfRepresentationCreator {
    void setIndexDir(String dir);
    List<ClassificationInput> createTfIdfDocumentsRepresentations();
    
    void getIdfValuesForTerms() throws IOException;
    List<ClassificationInput> getTfValuesAndCreateTfIdfDocuments() throws IOException;
}
