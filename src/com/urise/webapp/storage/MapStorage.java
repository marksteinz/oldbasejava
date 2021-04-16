package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage{
    private static final Map<String, Resume> MAP_STORAGE = new LinkedHashMap<>();

    @Override
    protected Boolean isExist(Object key) {
        return key != null;
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
    protected Object findElementKey(String uuid) {
        return MAP_STORAGE.containsKey(uuid) ? uuid : null;
    }

    @Override
    public void clear() {
        MAP_STORAGE.clear();
    }

    @Override
    public Resume[] getAll() {
        return MAP_STORAGE.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return MAP_STORAGE.size();
    }
}
