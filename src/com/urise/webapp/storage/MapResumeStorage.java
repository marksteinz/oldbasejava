package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume>{
    private static final Map<String, Resume> MAP_RESUME_STORAGE = new HashMap<>();

    @Override
    protected boolean isExist(Resume index) {
        return index != null;
    }

    @Override
    protected Resume getElement(Resume resume) {
        return resume;
    }

    @Override
    protected void saveElement(Resume resume, Resume index) {
        MAP_RESUME_STORAGE.put(resume.getUuid(), resume);
    }

    @Override
    protected void updateElement(Resume resume, Resume index) {
        MAP_RESUME_STORAGE.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteElement(Resume index) {
        MAP_RESUME_STORAGE.remove((index).getUuid());
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
