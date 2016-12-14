/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.feature_selection;

/**
 *
 * @author Sandra Stojanović
 */
public class FeatureSelectorFactory {
    public static FeatureSelector makeFeatureSelector(String type){
        switch(type){
            case "mutual information":
                return new MutualInformationFeatureSelector();
            case "frequency based":
                return new FrequencyBasedFeatureSelector();
            default:
                return new FrequencyBasedFeatureSelector();
        }
    }
}