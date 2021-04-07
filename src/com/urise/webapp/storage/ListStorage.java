package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected static final List<Resume> list = new ArrayList<>(3);

    @Override
    protected void deleteElement(int index) {
        list.remove(index);
    }

    @Override
    protected void updateElement(Resume resume, int index) {
        list.set(index, resume);
    }

    public void clear() {
        list.clear();
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = findElementIndex(uuid);
        if (resume.getUuid() == null) {
            System.out.println("Error: null uuid");
            return;
        }
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        list.add(resume);
    }

    public Resume get(String uuid) {
        int index = findElementIndex(uuid);
        if (index >= 0) {
            return list.get(index);
        }
        throw new NotExistStorageException(uuid);
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(list.toArray(new Resume[0]), 0, list.size());
    }

    public int size() {
        return list.size();
    }

    protected int findElementIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
