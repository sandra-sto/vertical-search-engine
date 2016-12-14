/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classification.feature_selection;

import java.util.List;

/**
 *
 * @author Sandra Stojanović
 */
public interface Heap<T> {
    int getParent(int pos); 
    int getLeftChild(int pos);
    int getRightChild(int pos);
    
    void swap(int pos1, int pos2);
    void shiftDown(int pos);
    
    void insert(T el);
    void insertAll(List<T> list);
    T remove();
    
    boolean isLeaf(int pos);
    boolean isRoot(int pos);
}
