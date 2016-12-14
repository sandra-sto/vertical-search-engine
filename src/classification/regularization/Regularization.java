/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.regularization;

import java.io.Serializable;

/**
 *
 * @author Sandra Stojanović
 */
public abstract class Regularization implements Serializable{
    protected double lambda = 50; 
    public abstract double getRegularizationPartialDerivative(double weight);
}