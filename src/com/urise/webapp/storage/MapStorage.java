package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage<String>{
    private static final Map<String, Resume> MAP_STORAGE = new HashMap<>();

    @Override
    protected boolean isExist(String key) {
        return MAP_STORAGE.containsKey(key);
    }

    @Override
    protected Resume getElement(String key) {
        return MAP_STORAGE.get(key);
    }

    @Override
    protected void saveElement(Resume resume, String index) {
        MAP_STORAGE.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(Resume resume, String index) {
        MAP_STORAGE.replace(index, resume);
    }

    @Override
    protected void deleteElement(String index) {
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
