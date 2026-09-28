package com.thelastimperial.storage.service.services;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * LocalStorageService implementation of StorageService to save file in local directory.
*/
@Slf4j
public class LocalStorageService implements StorageService {
    private final Path rootPath;

    public LocalStorageService(String storagePath) {
        log.debug("Path to save data: {}", storagePath);
        this.rootPath = Paths.get(storagePath);
    }

    @Override
    public void save(InputStream input, String name) throws Exception {
        Path dest = rootPath.resolve(name).normalize().toAbsolutePath();
        Files.copy(input, dest, StandardCopyOption.REPLACE_EXISTING );
    }

    @Override
    public File get(String name){
        Path path = rootPath.resolve( name ).normalize().toAbsolutePath();
        return path.toFile();
    }
    @Override
    public void delete(String name) {
        Path path = rootPath.resolve( name ).normalize().toAbsolutePath();
        path.toFile().delete();
    }

}
