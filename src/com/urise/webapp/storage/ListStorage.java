package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private static final List<Resume> list = new ArrayList<>();

    public Resume[] getAll() {
        return list.toArray(new Resume[0]);
    }

    public int size() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    @Override
    protected void deleteElement(int index) {
        list.remove(index);
    }

    @Override
    protected void updateElement(Resume resume, int index) {
        list.set(index, resume);
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        list.add(resume);
    }

    @Override
    public Resume getElement(int index) {
        return list.get(index);
    }

    @Override
    protected int findElementIndex(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
