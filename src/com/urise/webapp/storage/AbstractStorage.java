package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object findElementKey(String uuid);

    protected abstract Boolean isExist(Object key);

    protected abstract Resume getElement(Object key);

    protected abstract void saveElement(Resume resume, Object index);

    protected abstract void updateElement(Resume resume, Object index);

    protected abstract void deleteElement(Object index);


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
        Object key = findElementKey(uuid);
        if (isExist(key)) {
            return key;
        }
        throw new NotExistStorageException(uuid);
    }

    private Object getNotExistKey(String uuid){
        Object key = findElementKey(uuid);
        if (!isExist(key)) {
            return key;
        }
        throw new ExistStorageException(uuid);
    }
}
