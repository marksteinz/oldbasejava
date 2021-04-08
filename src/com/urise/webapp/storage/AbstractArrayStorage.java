package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected final static Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void updateElement(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected void sameSave(Resume resume, int index, String uuid) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Error: overflow storage", uuid);
        }
        insertElement(resume, index);
        size++;
    }

    @Override
    public Resume getElement(int index) {
        return storage[index];
    }

    protected abstract void deleteElement(int index);
}
