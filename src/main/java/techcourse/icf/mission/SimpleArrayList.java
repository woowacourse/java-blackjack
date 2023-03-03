package techcourse.icf.mission;

import java.util.Arrays;

public class SimpleArrayList<T> implements SimpleList<T> {

    private static int DEFAULT_CAPACITY = 10;

    private int size;
    private int capacity;
    private T[] values;

    public SimpleArrayList() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void add(int index, T value, int s) {
        if (s == capacity) {
            capacity += capacity / 2;
            values = Arrays.copyOf(values, capacity);
        }
        System.arraycopy(values, index, values, index + 1, s - index);
        values[index] = value;
        size = s + 1;
    }

    private void remove(int index, int s) {
        if (size - 1 > index) {
            System.arraycopy(values, index + 1, values, index, size);
        }
        values[size - 1] = null;
        size = s - 1;
    }

    @Override
    public boolean add(T value) {
        add(size, value, size);
        return true;
    }

    @Override
    public void add(int index, T value) {
        add(index, value, size);
    }

    @Override
    public T set(int index, T value) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        values[index] = value;
        return value;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return values[index];
    }

    @Override
    public boolean contains(Object value) {
        return Arrays.stream(values).anyMatch((val) -> val.equals(value));
    }

    @Override
    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
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
    public boolean remove(Object value) {
        if (indexOf(value) == -1) {
            return false;
        }
        remove(indexOf(value), size);
        return true;
    }

    @Override
    public T remove(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T result = get(index);
        remove(index, size);
        return result;
    }

    @Override
    public void clear() {
        size = 0;
        capacity = DEFAULT_CAPACITY;
        values = (T[]) new Object[capacity];
    }
}
