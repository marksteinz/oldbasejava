package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = findElementIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        int index = findElementIndex(resume.getUuid()) + 1;
        if (size > storage.length) {
            System.out.println("Error: overstack storage");
            return;
        }
        if (resume.getUuid() == null) {
            System.out.println("Error: null uuid");
            return;
        }
        if (findElementIndex(resume.getUuid()) >= 0){
            System.out.println("Error: " + index + " not unique resume");
            return;
        }
        storage[size] = resume;
        size++;
    }

    public Resume get(String uuid) {
        int index = findElementIndex(uuid);
        return index >= 0 ? storage[index] : null;
    }

    public void delete(String uuid) {
        int index = findElementIndex(uuid);
        if (size - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
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
        for (int i = 0; i < size; i++){
            if (storage[i].getUuid().equals(uuid)){
                return i;
            }
        }
        return -1;
    }
}