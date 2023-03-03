package techcourse.jcf.mission;

import java.util.Arrays;

public class SimpleArrayList<T> implements SimpleList<T> {

    private static final int initialValue = 10;

    private T[] arrayList;
    private int maxSize = initialValue;
    private int size = 0;

    public SimpleArrayList() {
        arrayList = (T[]) new Object[initialValue];
    }

    @Override
    public boolean add(T value) {
        size++;
        addValue(size - 1, value);
        return true;
    }

    @Override
    public void add(int index, T value) {
        size++;
        addValue(index, value);
    }

    private void addValue(int index, T value) {
        validateIndex(index);
        if (size == maxSize) {
            grow();
        }
        arrayList[index] = value;
    }

    private void grow() {
        maxSize = maxSize * 2;
        arrayList = Arrays.copyOf(arrayList, maxSize);
    }

    private void validateIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T set(int index, T value) {
        validateIndex(index);
        arrayList[index] = value;
        return arrayList[index];
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return arrayList[index];
    }

    @Override
    public boolean contains(T value) {
        boolean isFound = false;
        for (int i = 0; i < size && !isFound; i++) {
            isFound = arrayList[i] == value;
        }
        return isFound;
    }

    @Override
    public int indexOf(T value) {
        boolean isFound = false;
        int index = 0;
        for (; index < size && !isFound; index++) {
            isFound = arrayList[index] == value;
        }
        if (isFound) {
            return index - 1;
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
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        removeByIndex(index);
        return true;
    }

    private void removeByIndex(int index) {
        for (; index < size - 1; index++) {
            arrayList[index] = arrayList[index + 1];
        }
        size--;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T value = arrayList[index];
        removeByIndex(index);
        return value;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arrayList[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        return arrayList.toString();
    }
}
