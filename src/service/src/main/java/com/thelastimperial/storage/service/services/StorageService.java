package com.thelastimperial.storage.service.services;

import java.io.File;
import java.io.InputStream;
/**
 *
 * StorageService interface to manage the file.
*/
public interface StorageService {
    /**
     * Save the file on the container selected.
     * @param input The InputStream of the file
     * @param name The name to be saved.
     * @throws Exception The method could throws an exception if fails saving the file.
    */
    public void save(InputStream input, String name) throws Exception;
    /**
     * Get the file by the name.
     * @param name
     * @return a File object.
    */
    public File get(String name);
    /**
     * Delete the file of the system.
     * @param name the file name that going to be deleted.
    */
    public void delete(String name);
}
