package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void delete(String uuid) {
        int index = findElementIndex(uuid);
        if (index >= 0) {
            deleteElement(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = findElementIndex(uuid);
        if (index >= 0) {
            updateElement(resume, index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void updateElement(Resume resume, int index);

    protected abstract void deleteElement(int index);

    protected abstract int findElementIndex(String uuid);
}
