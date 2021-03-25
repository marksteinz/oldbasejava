package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

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

    protected int findElementIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}