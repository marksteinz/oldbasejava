package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object findElementKey(String uuid);

    protected abstract Boolean isExist(Object key);

    protected abstract Resume getElement(Object key);

    protected abstract void saveElement(Resume resume, Object index);

    protected abstract void updateElement(Resume resume, Object index);

    protected abstract void deleteElement(Object index);

    protected abstract List<Resume> doCopyAll();


    public Resume get(String uuid) {
        return getElement(getExistKey(uuid));
    }

    public void delete(String uuid) {
        deleteElement(getExistKey(uuid));
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        if (uuid == null) {
            System.out.println("Error: null uuid");
            return;
        }
        saveElement(resume, getNotExistKey(uuid));
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        updateElement(resume, getExistKey(uuid));
    }

    private Object getExistKey(String uuid){
        Object Index = findElementKey(uuid);
        if (isExist(Index)) {
            return Index;
        }
        throw new NotExistStorageException(uuid);
    }

    private Object getNotExistKey(String uuid){
        Object Index = findElementKey(uuid);
        if (!isExist(Index)) {
            return Index;
        }
        throw new ExistStorageException(uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}
