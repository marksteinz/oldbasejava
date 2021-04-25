package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private static final List<Resume> LIST_STORAGE = new ArrayList<>();

    public List<Resume> doCopyAll() {
        return new ArrayList<>(LIST_STORAGE);
    }

    public int size() {
        return LIST_STORAGE.size();
    }

    public void clear() {
        LIST_STORAGE.clear();
    }

    @Override
    protected void deleteElement(Object index) {
        LIST_STORAGE.remove((int) index);
    }

    @Override
    protected void updateElement(Resume resume, Object index) {
        LIST_STORAGE.set((int) index, resume);
    }

    @Override
    protected void saveElement(Resume resume, Object index) {
        LIST_STORAGE.add(resume);
    }

    @Override
    protected Boolean isExist(Object key) {
        return key != null;
    }

    @Override
    public Resume getElement(Object index) {
        return LIST_STORAGE.get((int) index);
    }

    @Override
    protected Object findElementKey(String uuid) {
        for (int i = 0; i < LIST_STORAGE.size(); i++) {
            if (LIST_STORAGE.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
