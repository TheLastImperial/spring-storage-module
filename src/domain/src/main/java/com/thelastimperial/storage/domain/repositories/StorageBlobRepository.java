package com.thelastimperial.storage.domain.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thelastimperial.storage.domain.entities.StorageBlobEntity;

public interface StorageBlobRepository extends JpaRepository<StorageBlobEntity, UUID>{
}
