package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected static final Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void saveSame(Resume resume, Object index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Error: overflow storage", resume.getUuid());
        }
        insertElement(resume, (int) index);
        size++;
    }

    protected abstract void insertElement(Resume resume, int index);

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void updateElement(Resume resume, Object index) {
        storage[(int) index] = resume;
    }

    @Override
    public Resume getElement(Object index) {
        return storage[(int) index];
    }

    @Override
    public Boolean checkKey(Object key) {
        return (int) key >= 0;
    }

    @Override
    public void deleteElement(Object key){
        deleteArrayElement((int) key);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void deleteArrayElement(int index);
}
