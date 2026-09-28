package com.thelastimperial.storage.web.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import com.thelastimperial.storage.service.services.StorageHandler;
import com.thelastimperial.storage.web.controllers.StorageController;

@AutoConfiguration
public class StorageWebAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(name = "storageController")
    public StorageController storageController(StorageHandler storageHandler) {
        return new StorageController(storageHandler);
    }
}
