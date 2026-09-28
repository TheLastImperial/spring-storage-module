package com.thelastimperial.storage.service.services;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.thelastimperial.storage.domain.entities.StorageBlobEntity;
import com.thelastimperial.utils.crypto.Checksum;

import lombok.extern.slf4j.Slf4j;
/**
 *
 * StorageHandler manage the Storage logic that include the StorageService and the
 * StorageBlobService.
*/
@Slf4j
public class StorageHandler {
    private final StorageBlobService<StorageBlobEntity> storageBlobService;
    private final StorageService storageService;
    private final Checksum checksum = new Checksum();

    public StorageHandler(
        StorageBlobService<StorageBlobEntity> storageBlobService,
        StorageService storageService
    ){
        this.storageBlobService = storageBlobService;
        this.storageService = storageService;
    }
    /**
     * Save the file
     * @param file File that going to be saved.
     * @return The object that represent the table entity.
     * @throws Exception exception if save file fails
    */
    public StorageBlobEntity save(MultipartFile file) throws Exception {
        StorageBlobEntity saved = storageBlobService.save(file);
        storageService.save(file.getInputStream(), saved.getId().toString());
        return saved;
    }
    /**
     * Get the Storage Entity
     * @param id to search the object.
     * @return and Optional that could have the object entity.
    */
    public Optional<StorageBlobEntity> get(UUID id){
        return storageBlobService.get(id);
    }

    /**
     * Delete the file of the system and DB
     * @param id the file to delete.
    */
    public void delete(UUID id) {
        storageBlobService.delete(id);
        storageService.delete(id.toString());
    }
    /**
     * Convert the Storage Entity to a Resource
     * @param storageBlobEntity Entity to convert
     * @return A Resource Object.
    */
    public Resource getResource(StorageBlobEntity storageBlobEntity) {
        File file = storageService.get(storageBlobEntity.getId().toString());
        Resource resource = new FileSystemResource(file);
        String checksum = "";
        try{
            checksum = this.checksum.digest(resource.getInputStream());
        }catch(Exception e) {
            log.error("Error traying to get InputStream");
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if(!storageBlobEntity.getChecksum().equals(checksum)){
            log.error("Dismatch checksum for storage");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return resource;
    }
}
