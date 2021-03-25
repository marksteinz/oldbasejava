package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage{

    public void save(Resume resume) {
        int index = findElementIndex(resume.getUuid());
        if (size >= storage.length) {
            System.out.println("Error: overflow storage");
            return;
        }
        if (resume.getUuid() == null) {
            System.out.println("Error: null uuid");
            return;
        }
        if (findElementIndex(resume.getUuid()) >= 0) {
            System.out.println("Error: " + resume.getUuid() + " not unique resume");
        } else {
            index = -index - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = resume;
            size++;
        }
    }

    protected int findElementIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0 , size, searchKey);
    }
}
