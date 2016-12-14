/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.feature_selection;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sandra Stojanović
 */
public class FeaturesMaxHeap implements Heap<FeatureSelectionTerm>{
    List<FeatureSelectionTerm> heap;
    
    public FeaturesMaxHeap(){
        heap = new ArrayList();
    }
    @Override
    public int getParent(int pos){
        return (pos - 1) >> 1;
    }
    
    @Override
    public int getLeftChild(int pos){
        return (pos << 1) + 1;
    }
    
    @Override
    public int getRightChild(int pos){
        return (pos << 1) + 2;
    }
    
    @Override
    public void swap(int pos1, int pos2){
        FeatureSelectionTerm tmp = heap.get(pos1);
        heap.set(pos1, heap.get(pos2));
        heap.set(pos2, tmp);
    }
    
    @Override
    public boolean isRoot(int pos){
        return pos == 0;
    }
    
    @Override
    public void insert(FeatureSelectionTerm el){
        int pos = getSize();
        heap.add(el);
        int parentPos = getParent(pos);
        while(!isRoot(pos) && el.greaterThan(heap.get(parentPos)))   {
            swap(pos, parentPos);
            pos = parentPos;
            parentPos = getParent(pos);
         }	
    }

    @Override
    public FeatureSelectionTerm remove(){
        FeatureSelectionTerm popped = heap.get(0);
        int lastPos = getSize() - 1;
        heap.set(0, heap.get(lastPos));
        heap.remove(lastPos);
        shiftDown(0);
        return popped;
    }

    private int getSize(){
        return heap.size();
    }
    
    @Override
    public boolean isLeaf(int pos){
        int size = getSize();
        return pos >= (size >> 1) && pos < size;
    }
    
    @Override
    public void shiftDown(int pos) {     
        int biggest = pos;
        boolean foundBiggerChild = true;
        while(!isLeaf(pos) && foundBiggerChild) {
            int left = getLeftChild(pos);
            int right = getRightChild(pos);
          
            if (left < getSize() && heap.get(left).greaterThan(heap.get(pos))){
                biggest = left;
            }  
            if (right < getSize() && heap.get(right).greaterThan(heap.get(biggest))){
                biggest = right;               
            }            
            if(biggest != pos){
                swap(biggest, pos);
                pos = biggest;
                foundBiggerChild = true;
            }else 
                foundBiggerChild = false;         
        }
    }  

    @Override
    public void insertAll(List<FeatureSelectionTerm> list) {
        for(FeatureSelectionTerm term : list){
            insert(term);
        }
    }
}