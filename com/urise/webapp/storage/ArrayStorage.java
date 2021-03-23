package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10_000;

    private final static Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size = 0;

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

    public void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("Error: overflow storage");
            return;
        }
        if (resume == null) {
            System.out.println("Error: null resume");
            return;
        }
        if (resume.getUuid() == null) {
            System.out.println("Error: null uuid");
            return;
        }
        if (findElementIndex(resume.getUuid()) >= 0) {
            System.out.println("Error: " + resume.getUuid() + " not unique resume");
            return;
        }
        storage[size] = resume;
        size++;
    }

    public Resume get(String uuid) {
        int index = findElementIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("Error: " + uuid + " not found");
        return null;
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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int findElementIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}