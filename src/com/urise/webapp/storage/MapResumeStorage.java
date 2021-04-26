package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage{
    private static final Map<String, Resume> MAP_RESUME_STORAGE = new HashMap<>();

    @Override
    protected boolean isExist(Object index) {
        return index != null;
    }

    @Override
    protected Resume getElement(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected void saveElement(Resume resume, Object index) {
        MAP_RESUME_STORAGE.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(Resume resume, Object index) {
        MAP_RESUME_STORAGE.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteElement(Object index) {
        MAP_RESUME_STORAGE.remove(((Resume) index).getUuid());
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(MAP_RESUME_STORAGE.values());
    }

    @Override
    protected Resume findElementKey(String uuid) {
        return MAP_RESUME_STORAGE.get(uuid);
    }

    @Override
    public void clear() {
        MAP_RESUME_STORAGE.clear();
    }

    @Override
    public int size() {
        return MAP_RESUME_STORAGE.size();
    }
}
