package com.thelastimperial.storage.service.autoconfiguration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import com.thelastimperial.storage.domain.entities.StorageBlobEntity;
import com.thelastimperial.storage.domain.repositories.StorageBlobRepository;
import com.thelastimperial.storage.service.services.JpaStorageBlobService;
import com.thelastimperial.storage.service.services.LocalStorageService;
import com.thelastimperial.storage.service.services.StorageBlobService;
import com.thelastimperial.storage.service.services.StorageHandler;
import com.thelastimperial.storage.service.services.StorageService;

@AutoConfiguration
public class StorageServiceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name="storageHandler")
    public StorageHandler storageHandler(
        StorageBlobService<StorageBlobEntity> storageBlobService,
        StorageService storageService
    ){
        return new StorageHandler(storageBlobService, storageService);
    }

    @Bean
    @ConditionalOnMissingBean(name="storageBlobService")
    public StorageBlobService<StorageBlobEntity> storageBlobService(
        StorageBlobRepository storageBlobRepository
    ) {
        return new JpaStorageBlobService(storageBlobRepository);
    }

    @Bean
    @ConditionalOnMissingBean(name="storageService")
    public StorageService storageService(
        @Value("${com.thelastimperial.storage.service.storage.path}") String storagePath
    ){
        return new LocalStorageService(storagePath);
    }

}
