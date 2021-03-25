package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
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

    public void update(Resume resume) {
        int index = findElementIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Error: " + resume.getUuid() + " not found");
        }
    }

    public void delete(String uuid) {
        int index = findElementIndex(uuid);
        if (index >= 0) {
            if (size - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            size--;
        } else {
            System.out.println("Error: " + uuid + " not found");
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
        System.out.println("Error: " + uuid + " not found");
        return null;
    }

    protected abstract int findElementIndex(String uuid);
}
