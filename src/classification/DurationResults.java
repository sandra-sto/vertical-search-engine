/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sandra Stojanović
 */
public class DurationResults {
    String parameterMeasured;
    long startTime;
    Map<String, Float> durations;
    String measure = "ms";
 
    public static DurationResults instance;
    private DurationResults(){
        durations = new HashMap<String, Float>();
    }
    
    public static DurationResults getInstance(){
        if (instance == null)
            instance = new DurationResults();
        return instance;
    }
    public void setStartTime(String parameter){
        this.parameterMeasured = parameter;
        this.startTime = System.nanoTime();
    }
    
    public void setEndTime(){
        long duration = System.nanoTime()- startTime;
        durations.put(parameterMeasured, (float)(duration / 1000000));
    }
    
    public void clearDurationResults(){
        durations.clear();
    }
    public String getDurationResults(){
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String, Float> entry : durations.entrySet()){
            builder.append(entry.getKey() + ": " + entry.getValue() + " " + measure)
                  .append("\n");    
        }
        return builder.toString();
    }
    
    public String getDurationResult(String parameter){
        if(durations.containsKey(parameter))
            return durations.get(parameter) + measure;
        else return "";
    }
    
    public void printDurationResults(){
        System.out.println(getDurationResults());
    }
}