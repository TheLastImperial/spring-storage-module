package com.thelastimperial.storage.domain.autoconfiguration;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories (basePackages = {
    "com.thelastimperial.storage.domain.repositories"
})
@EntityScan(basePackages = {
    "com.thelastimperial.storage.domain.entities"
})
public class StorageDomainAutoConfiguration {
}
