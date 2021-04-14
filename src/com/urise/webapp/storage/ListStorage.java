package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
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
    protected void deleteElement(Object index) {
        list.remove((int) index);
    }

    @Override
    protected void updateElement(Resume resume, Object index) {
        list.set((int) index, resume);
    }

    @Override
    protected void saveSame(Resume resume, Object index) {
        list.add(resume);
    }

    @Override
    protected Boolean checkKey(Object key) {
        return key != null;
    }

    @Override
    public Resume getElement(Object index) {
        return list.get((int) index);
    }

    @Override
    protected Object findElementKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
