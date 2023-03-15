package mission;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class SimpleArrayList<E> implements SimpleList<E> {
    private static final int INITIAL_CAPACITY = 10;

    private Object[] values;
    private final int capacity;


    public SimpleArrayList() {
        this.values = new Object[INITIAL_CAPACITY];
        this.capacity = values.length;
    }

    @Override
    public boolean add(E value) {
        int size = size();
        if (size >= capacity) {
            increaseCapacity();
            values[size] = value;
            return true;
        }
        values[size] = value;
        return true;
    }

    @Override
    public void add(int index, E value) {
        int size = size();
        if (size >= capacity) {
            increaseCapacity();
        }
        E[] currentValues = copyCurrentValues();
        values[index] = value;
        for (int i = index + 1; i <= size; i++) {
            values[i] = currentValues[i - 1];
        }
    }

    private E[] copyCurrentValues() {
        Object[] currentValues = new Object[capacity];
        for (int i = 0; i < size(); i++) {
            currentValues[i] = values[i];
        }
        return (E[]) currentValues;
    }

    private void increaseCapacity() {
        E[] currentValues = (E[]) values;
        values = new Object[capacity * 2];
        for (int index = 0; index < capacity; index++) {
            values[index] = currentValues[index];
        }
    }

    @Override
    public E set(int index, E value) {
        E currentValue = (E) values[index];
        values[index] = value;
        return currentValue;
    }

    @Override
    public E get(int index) {
        validateIndexInBounds(index);
        return (E) values[index];
    }

    private void validateIndexInBounds(final int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean contains(E value) {
        for (Object element : values) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(E value) {
        for (int index = 0; index < size(); index++) {
            if (values[index].equals(value)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        int size = 0;
        for (Object element : values) {
            if (element != null) {
                size++;
            }
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean remove(E value) {
        try {
            int index = indexOf(value);
            remove(index);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }

    }

    @Override
    public E remove(int index) {
        validateIndexInBounds(index);
        E removeValue = (E) values[index];
        Object[] currentValues = copyCurrentValues();
        for (int i = index; i < size() - 1; i++) {
            values[i] = currentValues[i + 1];
        }
        values[size() - 1] = null;
        return removeValue;
    }

    @Override
    public void clear() {
        for (int index = 0; index < size(); index++) {
            values[index] = null;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}
