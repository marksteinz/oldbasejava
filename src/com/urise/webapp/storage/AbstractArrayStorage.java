package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10_000;
    protected static final Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size = 0;

    protected abstract void saveToArray(Resume resume, int index);

    protected abstract void deleteFromArray(int index);


    public int size() {
        return size;
    }

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void saveElement(Resume resume, Integer index) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Error: overflow storage", resume.getUuid());
        }
        saveToArray(resume, index);
        size++;
    }

    @Override
    protected void updateElement(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    public Resume getElement(Integer index) {
        return storage[index];
    }

    @Override
    public boolean isExist(Integer key) {
        return key >= 0;
    }

    @Override
    public void deleteElement(Integer key){
        deleteFromArray(key);
        storage[size - 1] = null;
        size--;
    }
}
