package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
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
    protected void deleteElement(Integer index) {
        LIST_STORAGE.remove(index.intValue());
    }

    @Override
    protected void updateElement(Resume resume, Integer index) {
        LIST_STORAGE.set(index, resume);
    }

    @Override
    protected void saveElement(Resume resume, Integer index) {
        LIST_STORAGE.add(resume);
    }

    @Override
    protected boolean isExist(Integer key) {
        return key != null;
    }

    @Override
    public Resume getElement(Integer index) {
        return LIST_STORAGE.get(index);
    }

    @Override
    protected Integer findElementKey(String uuid) {
        for (int i = 0; i < LIST_STORAGE.size(); i++) {
            if (LIST_STORAGE.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
