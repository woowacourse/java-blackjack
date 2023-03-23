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
        return false;
    }

    @Override
    public int indexOf(final T value) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean remove(final T value) {
        return false;
    }

    @Override
    public T remove(final int index) {
        return null;
    }

    @Override
    public void clear() {

    }

    private void extendCapacity() {
        this.values = Arrays.copyOf(this.values, currentCapacity * 2);
    }

    public T[] getValues() {
        return values;
    }
}
