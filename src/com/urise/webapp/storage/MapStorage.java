package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage{
    private static final Map<String, Resume> MAP_STORAGE = new HashMap<>();

    @Override
    protected Boolean isExist(Object key) {
        return MAP_STORAGE.containsKey(key);
    }

    @Override
    protected Resume getElement(Object key) {
        return MAP_STORAGE.get(key);
    }

    @Override
    protected void saveElement(Resume resume, Object index) {
        MAP_STORAGE.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(Resume resume, Object index) {
        MAP_STORAGE.replace((String) index, resume);
    }

    @Override
    protected void deleteElement(Object index) {
        MAP_STORAGE.remove(index);
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(MAP_STORAGE.values());
    }

    @Override
    protected String findElementKey(String uuid) {
        return uuid;
    }

    @Override
    public void clear() {
        MAP_STORAGE.clear();
    }

    @Override
    public int size() {
        return MAP_STORAGE.size();
    }
}
