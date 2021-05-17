package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.serializer.StreamSerializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;

    private StreamSerializer serializer;

    protected PathStorage(String dir, StreamSerializer streamSerializer) {
        Objects.requireNonNull(dir, "directory must not be null");

        this.serializer = streamSerializer;
        directory = Paths.get(dir);
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
    }

    @Override
    public void clear() {
        getFilesList().forEach(this::deleteElement);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    @Override
    protected Path findElementKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void updateElement(Resume resume, Path path) {
        try {
            serializer.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void saveElement(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create Path " + path, getFileName(path), e);
        }
        updateElement(resume, path);
    }

    @Override
    protected Resume getElement(Path path) {
        try {
            return serializer.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Path read error", getFileName(path), e);
        }
    }


    @Override
    protected void deleteElement(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", getFileName(path), e);
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        return getFilesList().map(this::getElement).collect(Collectors.toList());
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Directory read error", e);
        }
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }
}
