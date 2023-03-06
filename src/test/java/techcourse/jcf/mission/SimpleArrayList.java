package techcourse.jcf.mission;

import java.util.Arrays;

public class SimpleArrayList<T> implements SimpleList<T> {
    private static final int BASIC_ARRAY_SIZE = 10;
    private T[] values;
    private int size;

    @SuppressWarnings("unchecked")
    public SimpleArrayList() {
        this.values = (T[]) new Object[BASIC_ARRAY_SIZE];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public SimpleArrayList(T... values) {
        this.values = (T[]) new Object[values.length * 2];
        this.size = values.length;
        System.arraycopy(values, 0, this.values, 0, values.length);
    }

    @Override
    public boolean add(T value) {
        if (size >= values.length) {
            makeArraySizeDouble();
        }
        values[size++] = value;
        return true;
    }

    @SuppressWarnings("unchecked")
    private void makeArraySizeDouble() {
        T[] result = (T[]) new Object[this.values.length * 2];
        System.arraycopy(this.values, 0, result, 0, this.values.length);
        this.values = result;
    }

    @Override
    public void add(int index, T value) {
        if (isIndexOutOfRange(index)) {
            throw new ArrayIndexOutOfBoundsException("인덱스는 배열의 크기보다 작아야 합니다.");
        }
        T[] tempValues = Arrays.copyOfRange(values, index, values.length);
        System.arraycopy(tempValues, 0, values, index + 1, tempValues.length);
        values[index] = value;
        size++;
    }

    private boolean isIndexOutOfRange(int index) {
        return index >= size;
    }

    @Override
    public T set(int index, T value) {
        if (isIndexOutOfRange(index)) {
            throw new ArrayIndexOutOfBoundsException("인덱스는 배열의 크기보다 작아야 합니다.");
        }
        T oldValue = values[index];
        values[index] = value;
        return oldValue;
    }

    @Override
    public T get(int index) {
        if (isIndexOutOfRange(index)) {
            throw new ArrayIndexOutOfBoundsException("인덱스는 배열의 크기보다 작아야 합니다.");
        }
        return values[index];
    }

    @Override
    public boolean contains(T value) {
        for (T arrayListValue : values) {
            if (arrayListValue.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < values.length; i++) {
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
    public boolean remove(T value) {
        if (!contains(value)) {
            throw new IllegalStateException("배열에 존재하지 않는 값입니다.");
        }
        remove(indexOf(value));
        return true;
    }

    @Override
    public T remove(int index) {
        if (isIndexOutOfRange(index)) {
            throw new ArrayIndexOutOfBoundsException("인덱스는 배열의 크기보다 작아야 합니다.");
        }
        T oldValue = values[index];
        T[] tempValues = Arrays.copyOfRange(values, index + 1, values.length);
        System.arraycopy(tempValues, 0, values, index, tempValues.length);
        size--;
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        values = (T[]) new Object[0];
        size = 0;
    }
}

