package list;

public class CustomArrayList implements SimpleList {
    private static final int INITIAL_CAPACITY = 10;
    private String[] array;
    private int size;

    public CustomArrayList() {
        array = new String[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(String value) {
        ensureCapacity(size + 1);
        array[size++] = value;
        return true;
    }

    @Override
    public void add(int index, String value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of range");
        }
        ensureCapacity(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public String set(int index, String value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of range");
        }
        String oldValue = array[index];
        array[index] = value;
        return oldValue;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of range");
        }
        return array[index];
    }

    @Override
    public boolean contains(String value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(String value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(String value) {
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of range");
        }
        String removedValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removedValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = array.length * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            String[] newArray = new String[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
}