package techcourse.jcf.mission;

import java.util.Arrays;

public class SimpleArrayList implements SimpleList {

    private static final String[] SIMPLE_ARRAY = {};
    private static final int INITIAL_CAPACITY = 10;

    private int size;
    private int capacity;
    private String[] array;

    public SimpleArrayList() {
        this.array = new String[INITIAL_CAPACITY];
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
    }

    public SimpleArrayList(final int capacity) {
        this.array = new String[INITIAL_CAPACITY];
        this.capacity = capacity;
        this.size = 0;
    }

    // complete
    @Override
    public boolean add(final String value) {
        checkMaxSize();
        array[size] = value;
        size++;
        return true;
    }

    @Override
    public void add(final int index, final String value) {
        checkValidIndex(index);
        checkMaxSize();

        String[] tempArray = Arrays.copyOfRange(array, index, size);
        array[index] = value;
        for (int i = 0; i < tempArray.length; i++) {
            array[index + 1 + i] = tempArray[i];
        }
        size++;
    }

    // complete
    @Override
    public String set(final int index, final String value) {
        checkValidIndex(index);
        String oldValue = array[index];
        array[index] = value;
        return oldValue;
    }

    // complete
    @Override
    public String get(final int index) {
        checkValidIndex(index);
        return array[index];
    }

    // complete
    @Override
    public boolean contains(final String value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(final String value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    // complete
    @Override
    public int size() {
        return size;
    }

    // complete
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(final String value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public String remove(final int index) {
        checkValidIndex(index);

        return null;
    }

    // complete
    @Override
    public void clear() {
        this.capacity = INITIAL_CAPACITY;
        this.array = new String[INITIAL_CAPACITY];
        this.size = 0;
    }

    private void checkMaxSize() {
        if (size == capacity - 1) {
            grow();
        }
    }

    private void grow() {
        capacity *= 2;
    }

    private void checkValidIndex(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + "Size: " + size);
        }
    }
}
