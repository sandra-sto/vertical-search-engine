/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.multinomial_classifiers;

import classification.multinomial_classifiers.MultinomialClassificationAlgorithm;

/**
 *
 * @author Sandra Stojanović
 */
public interface ClassificationModelSerializator {
    public void serialize(MultinomialClassificationAlgorithm model, String path);
    public MultinomialClassificationAlgorithm deserialize(String path);
}
