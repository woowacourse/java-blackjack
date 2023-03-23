package generic;

import java.util.Arrays;

public class SimpleArrayList<T> implements SimpleList<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULT_ARRAYLIST = new Object[DEFAULT_CAPACITY];

    private int size = 0;
    private int currentCapacity = DEFAULT_CAPACITY;
    private T[] values;

    public SimpleArrayList() {
        values = (T[]) DEFAULT_ARRAYLIST;
    }

    public SimpleArrayList(final T[] values) {
        this.currentCapacity = values.length;
        this.size = values.length;
        this.values = values.clone();
    }

    @Override
    public boolean add(final T value) {
        if (size == currentCapacity) {
            extendCapacity();
        }
        values[size++] = value;
        return true;
    }

    @Override
    public void add(final int index, final T value) {
        if (size == currentCapacity) {
            extendCapacity();
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size += 1;
    }

    @Override
    public T set(final int index, final T value) {
        final T previousValue = values[index];
        values[index] = value;
        return previousValue;
    }

    @Override
    public T get(final int index) {
        return values[index];
    }

    @Override
    public boolean contains(final T value) {
        for (T storedValue : values) {
            if (storedValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(final T value) {
        for (int index = 0; index < size; index++) {
            if (values[index].equals(value)) {
                return index;
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
    public boolean remove(final T value) {
        for (int index = 0; index < size; index++) {
            if (values[index].equals(value)) {
                remove(index);
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(final int index) {
        final T removedValue = values[index];
        size -= 1;
        System.arraycopy(values, index + 1, values, index, size - index);
        values[size] = null;
        return removedValue;
    }

    @Override
    public void clear() {
        values = (T[]) new Object[0];
        size = 0;
    }

    private void extendCapacity() {
        this.values = Arrays.copyOf(this.values, currentCapacity * 2);
    }

    public T[] getValues() {
        return values;
    }
}
