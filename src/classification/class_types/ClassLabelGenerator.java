/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.class_types;

/**
 *
 * @author Sandra Stojanović
 */
public class ClassLabelGenerator {
    static int classLabel = -1;
    public static int generateClassLabel(){
        return ++classLabel;
    }
}
