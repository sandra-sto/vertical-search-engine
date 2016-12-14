/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.regularization;

import classification.regularization.Regularization;

/**
 *
 * @author Sandra Stojanović
 */
public class L2Regularization extends Regularization{

    @Override
    public double getRegularizationPartialDerivative(double weight) {
        return lambda * weight;
    }
}