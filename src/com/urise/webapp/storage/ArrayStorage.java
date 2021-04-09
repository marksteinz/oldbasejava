package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteElement(int index) {
        storage[index] = storage[size - 1];
        size--;
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Error: overflow storage", resume.getUuid());
        }
        storage[size] = resume;
        size++;
    }

    @Override
    protected int findElementIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}