package io.sited.web.impl;

import io.sited.resource.FileResource;
import io.sited.resource.Resource;
import io.sited.resource.ResourceException;
import io.sited.web.WebCache;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class WebCacheImpl implements WebCache {
    private final Path dir;

    public WebCacheImpl(Path dir) {
        this.dir = dir;
    }

    @Override
    public Optional<Resource> get(String path) {
        File file = dir.resolve(path).toFile();
        if (file.exists() && file.isFile()) {
            return Optional.of(new FileResource(path, file.toPath()));
        }
        return Optional.empty();
    }

    @Override
    public void create(Resource resource) {
        try {
            Path path = dir.resolve(resource.path());
            Files.createDirectories(path.getParent());
            Files.write(path, resource.toByteArray());
            path.toFile().setLastModified(resource.lastModified());
        } catch (IOException e) {
            throw new ResourceException(e);
        }
    }

    @Override
    public void delete(String path) {
        dir.resolve(path).toFile().delete();
    }
}