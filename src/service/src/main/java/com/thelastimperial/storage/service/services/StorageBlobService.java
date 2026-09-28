package com.thelastimperial.storage.service.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * StorageBlobService Save the file data on database.
 * @param <T> The object that going to save.
*/
public interface StorageBlobService<T> {
    /**
     * Save the file.
     * @param file File to save.
     * @return The Object that represent the file on database.
     * @throws Exception if save the file fails.
    */
    public T save(MultipartFile file) throws Exception;
    /**
     * Delete the file of DB by id.
     * @param id of the object to delete.
    */
    public void delete(UUID id);
    /**
     * Get the object by id
     * @param id to search.
     * @return Optional object that have the file Object representation.
    */
    public Optional<T> get(UUID id);
}
