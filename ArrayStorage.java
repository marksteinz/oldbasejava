/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    private int size = 0;

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r1) {
        storage[size] = r1;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid == uuid) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid == uuid) {
                index = i;
            }
        }
        for (int i = index; i < storage.length-1; i++) {
            storage[i] = storage[i+1];
        }
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[size];
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null)
                result[i] = storage[i];
        }
        return result;
    }

    int size() {
        return size;
    }
}