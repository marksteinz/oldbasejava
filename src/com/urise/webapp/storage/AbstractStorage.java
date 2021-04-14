package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        if (uuid == null) {
            System.out.println("Error: null uuid");
            return;
        }
        saveSame(resume, getNotExistKey(uuid));
    }

    public Resume get(String uuid) {
        return getElement(getExistKey(uuid));
    }

    public void delete(String uuid) {
        deleteElement(getExistKey(uuid));
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        updateElement(resume, getExistKey(uuid));
    }

    private Object getExistKey(String uuid){
        Object key = findElementKey(uuid);
        if (checkKey(key)) {
            return key;
        }
        throw new NotExistStorageException(uuid);
    }

    private Object getNotExistKey(String uuid){
        Object key = findElementKey(uuid);
        if (!checkKey(key)) {
            return key;
        }
        throw new ExistStorageException(uuid);
    }

    protected abstract Boolean checkKey(Object key);

    protected abstract Resume getElement(Object key);

    protected abstract void saveSame(Resume resume, Object index);

    protected abstract void updateElement(Resume resume, Object index);

    protected abstract void deleteElement(Object index);

    protected abstract Object findElementKey(String uuid);
}
