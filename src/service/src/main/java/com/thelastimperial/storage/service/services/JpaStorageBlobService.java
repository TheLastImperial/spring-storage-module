package com.thelastimperial.storage.service.services;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.thelastimperial.storage.domain.entities.StorageBlobEntity;
import com.thelastimperial.storage.domain.repositories.StorageBlobRepository;
import com.thelastimperial.utils.crypto.Checksum;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * JpaStorageBlobService implementation of StorageBlobService to manage data by JPA.
*/
@Service
@Slf4j
public class JpaStorageBlobService implements StorageBlobService<StorageBlobEntity>{
    private final StorageBlobRepository storageBlobRepository;
    private final Checksum checksum = new Checksum();

    public JpaStorageBlobService(
        StorageBlobRepository storageRepository
    ) {
        this.storageBlobRepository = storageRepository;
    }

    @Override
    public StorageBlobEntity save(MultipartFile file) throws Exception {
        StorageBlobEntity toSave = StorageBlobEntity.builder()
            .filename(file.getOriginalFilename())
            .contentType(file.getContentType())
            .byteSize(
                BigInteger.valueOf(file.getBytes().length)
            )
            .checksum(checksum.digest(file.getInputStream()))
            .build();

        return storageBlobRepository.save(toSave);
    }

    @Override
    public void delete(UUID id) {
        storageBlobRepository.deleteById(id);
    }

    @Override
    public Optional<StorageBlobEntity> get(UUID id) {
        return storageBlobRepository.findById(id);
    }

}
