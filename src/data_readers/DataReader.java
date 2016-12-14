/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_readers;

import java.io.File;
import java.io.FileFilter;
import java.util.List;

/**
 *
 * @author Sandra Stojanović
 */
public interface DataReader {
    List<File> readFiles(String path, FileFilter filter);
}