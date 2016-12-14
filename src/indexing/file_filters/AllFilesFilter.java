/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexing.file_filters;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author Sandra Stojanović
 */
public class AllFilesFilter implements FileFilter{
    
    @Override
    public boolean accept(File pathname) {
        return true;
    }   
}