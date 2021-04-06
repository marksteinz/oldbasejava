package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    protected final static Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = findElementIndex(uuid);
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Error: overflow storage", uuid);
        }
        if (uuid == null) {
            System.out.println("Error: null uuid");
            return;
        }
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        insertElement(resume, index);
        size++;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = findElementIndex(uuid);
        if (index >= 0) {
            storage[index] = resume;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void delete(String uuid) {
        int index = findElementIndex(uuid);
        if (index >= 0) {
            deleteElement(index);
            size--;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public Resume get(String uuid) {
        int index = findElementIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    protected abstract void deleteElement(int index);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract int findElementIndex(String uuid);
}
