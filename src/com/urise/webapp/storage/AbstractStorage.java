package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<FK> implements Storage {

    protected abstract FK findElementKey(String uuid);

    protected abstract boolean isExist(FK key);

    protected abstract Resume getElement(FK key);

    protected abstract void saveElement(Resume resume, FK index);

    protected abstract void updateElement(Resume resume, FK index);

    protected abstract void deleteElement(FK index);

    protected abstract List<Resume> doCopyAll();


    public Resume get(String uuid) {
        return getElement(getExistKey(uuid));
    }

    public void delete(String uuid) {
        deleteElement(getExistKey(uuid));
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        saveElement(resume, getNotExistKey(uuid));
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        updateElement(resume, getExistKey(uuid));
    }

    private FK getExistKey(String uuid){
        FK key = findElementKey(uuid);
        if (isExist(key)) {
            return key;
        }
        throw new NotExistStorageException(uuid);
    }

    private FK getNotExistKey(String uuid){
        FK key = findElementKey(uuid);
        if (!isExist(key)) {
            return key;
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
