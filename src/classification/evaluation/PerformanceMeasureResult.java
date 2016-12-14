/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.evaluation;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sandra Stojanović
 */
public class PerformanceMeasureResult {
    Map<String, Float> performanceMeasures;

    public PerformanceMeasureResult() {
        performanceMeasures = new HashMap<String, Float>();
    }
    
    public void addValue(String name, Float value){
        performanceMeasures.put(name, value);
    }
    public Float getValue(String name){
        return performanceMeasures.get(name);
    }
    
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String, Float> entry : performanceMeasures.entrySet()){
            builder.append(entry.getKey() + ": " + entry.getValue())
                  .append("\n");    
        }
        return builder.toString();
    }
}