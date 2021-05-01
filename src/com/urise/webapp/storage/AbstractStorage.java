package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<FK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract FK findElementKey(String uuid);

    protected abstract boolean isExist(FK key);

    protected abstract Resume getElement(FK key);

    protected abstract void saveElement(Resume resume, FK index);

    protected abstract void updateElement(Resume resume, FK index);

    protected abstract void deleteElement(FK index);

    protected abstract List<Resume> doCopyAll();


    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return getElement(getExistKey(uuid));
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        deleteElement(getExistKey(uuid));
    }

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        String uuid = resume.getUuid();
        saveElement(resume, getNotExistKey(uuid));
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        String uuid = resume.getUuid();
        updateElement(resume, getExistKey(uuid));
    }

    private FK getExistKey(String uuid){
        FK key = findElementKey(uuid);
        if (isExist(key)) {
            return key;
        }
        LOG.warning("Resume " + uuid + " not exist");
        throw new NotExistStorageException(uuid);
    }

    private FK getNotExistKey(String uuid){
        FK key = findElementKey(uuid);
        if (!isExist(key)) {
            return key;
        }
        LOG.warning("Resume " + uuid + " already exist");
        throw new ExistStorageException(uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}
