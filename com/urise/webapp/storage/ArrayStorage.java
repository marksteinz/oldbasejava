package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void update(Resume r){
        for (int i = 0; i < size; i++) {
            if(checkEquals(storage[i].getUuid(), r.getUuid())) {
                storage[i] = r;
            }
        }
    }

    public void save(Resume r) {
        if (size > storage.length) {
            System.out.println("Error: overstack storage");
            return;
        }
        if (r == null) {
            System.out.println("Error: null resume");
            return;
        }
        if (r.getUuid() == null) {
            System.out.println("Error: null uuid");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (checkEquals(storage[i].getUuid(), r.getUuid())) {
                System.out.println("Error: not unique resume");
                return;
            }
        }
        storage[size] = r;
        size++;
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (checkEquals(storage[i].getUuid(), uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (checkEquals(storage[i].getUuid(), uuid)) {
                index = i;
            }
        }
        for (int i = index; i < size; i++) {
            storage[i] = storage[i+1];
        }
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

    private boolean checkEquals(String uuid1, String uuid2) {
        return uuid1.equals(uuid2);
    }
}