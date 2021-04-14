package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;
import java.util.stream.Collectors;

public class MapStorage extends AbstractStorage{
    Map<String, Resume> map = new HashMap<>();

    @Override
    protected Boolean checkKey(Object key) {
        return key != null;
    }

    @Override
    protected Resume getElement(Object key) {
        return map.get(key);
    }

    @Override
    protected void saveSame(Resume resume, Object index) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(Resume resume, Object index) {
        map.replace((String) index, resume);
    }

    @Override
    protected void deleteElement(Object index) {
        map.remove(index);
    }

    @Override
    protected Object findElementKey(String uuid) {
        if (map.containsKey(uuid))
            return uuid;
        return null;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return map.size();
    }
}
