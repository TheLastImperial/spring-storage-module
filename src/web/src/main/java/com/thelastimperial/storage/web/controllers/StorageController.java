package com.thelastimperial.storage.web.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.thelastimperial.storage.domain.entities.StorageBlobEntity;
import com.thelastimperial.storage.service.services.StorageHandler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Controller
@RequestMapping("/r")
@Slf4j
public class StorageController {
    private final StorageHandler storageHandler;

    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> getResource(@PathVariable UUID fileId) {
        Optional<StorageBlobEntity> storageOpt = storageHandler.get(fileId);
        if(storageOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        StorageBlobEntity storage = storageOpt.get();

        log.debug("FileId: {}", fileId);
        Resource resource = storageHandler.getResource(storage);
        HttpHeaders headers = new HttpHeaders();

        headers.setContentDisposition(
            ContentDisposition
                .inline()
                .filename(storage.getFilename())
                .build()
        );
        return ResponseEntity.ok()
            .contentType(
                MediaType.valueOf(storage.getContentType())
            )
            // .header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
            .headers(headers)
            .body(resource);
    }

}
