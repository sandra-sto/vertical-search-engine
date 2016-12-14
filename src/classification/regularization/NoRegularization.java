/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.regularization;

/**
 *
 * @author Sandra Stojanović
 */
public class NoRegularization extends Regularization{

    @Override
    public double getRegularizationPartialDerivative(double weight) {
        return 0;
    }   
}