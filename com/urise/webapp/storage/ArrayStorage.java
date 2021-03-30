package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteElementIndex(String uuid, int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void insertElementIndex(Resume resume, int index) {
        storage[size] = resume;
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