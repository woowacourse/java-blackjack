package mission;

import java.util.Arrays;

public class SimpleArrayList<T> implements SimpleList<T> {

    public static final int INITIAL_CAPACITY = 10;
    private T[] values;
    int size;


    @SuppressWarnings("unchecked")
    public SimpleArrayList() {
        this.values = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(T value) {
        if (checkFull()) {
            grow();
        }
        values[size] = value;
        size++;
        return true;
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        T[] newValues = (T[]) new Object[values.length * 2];
        System.arraycopy(values, 0, newValues, 0, values.length);
        this.values = newValues;
    }

    private boolean checkFull() {
        return size == values.length - 1;
    }

    @Override
    public void add(int index, T value) {
        if (checkFull()) {
            grow();
        }
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("배열의 크기보다 큰 인덱스에 값을 삽입할 수 없습니다");
        }
        T[] tempValues = Arrays.copyOfRange(values, index, size);
        values[index] = value;
        index++;
        for (T tempValue : tempValues) {
            values[index] = tempValue;
            index++;
        }
        size++;

    }

    @Override
    public T set(int index, T value) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("배열의 크기보다 큰 index 에 값을 삽입할 수 없습니다.");
        }
        T oldValue = values[index];
        values[index] = value;
        return oldValue;
    }

    @Override
    public T get(int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("배열의 크기보다 index 의 값이 큽니다.");
        }
        return values[index];
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
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
    public boolean remove(T value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                for (; i < size; i++) {
                    values[i] = values[i + 1];
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
        T returnValue = values[index];
        for (; index < size; index++) {
            values[index] = values[index + 1];
        }
        size--;
        return returnValue;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        values = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }
}
