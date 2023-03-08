package minimission;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class SimpleArrayList<T> implements SimpleList<T>{

    private static final int DEFAULT_SIZE = 16;

    private T[] elements;
    private int size;
    private int position;

    public SimpleArrayList() {
        this.elements = (T[]) new Object[DEFAULT_SIZE];
        this.size = DEFAULT_SIZE;
        this.position = 0;
    }

    public SimpleArrayList(T... values) {
        this.elements = (T[]) new Object[DEFAULT_SIZE];
        this.size = DEFAULT_SIZE;
        this.position = 0;
        for (T value : values) {
            this.add(value);
        }
    }

    @Override
    public boolean add(T value) {
        checkCapacity();

        try {
            elements[position++] = value;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void add(int index, T value) {
        checkCapacity();
        validateIndexOutOfRange(index);

        position++;

        T[] newElements = (T[]) new Object[size];
        for (int i = 0; i < index - 1; i++) {
            newElements[i] = elements[i];
        }
        newElements[index] = value;
        for (int i = index + 1; i < position; i++) {
            newElements[i] = elements[i - 1];
        }

        elements = newElements;
    }

    @Override
    public T set(int index, T value) {
        validateIndexOutOfRange(index);

        T originalValue = elements[index];
        elements[index] = value;
        return originalValue;
    }

    @Override
    public T get(int index) {
        validateIndexOutOfRange(index);

        return elements[index];
    }

    @Override
    public boolean contains(T value) {
        for (int index = 0; index < position; index++) {
            if (elements[index].equals(value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int index = 0; index < position; index++) {
            if (elements[index].equals(value)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return position;
    }

    @Override
    public boolean isEmpty() {
        return position == 0;
    }

    @Override
    public boolean remove(T value) {
        if (!contains(value)) {
            throw new NoSuchElementException();
        }
        int index = indexOf(value);
        try {
            for (int i = index; i < position - 1; i++) {
                elements[i] = elements[i + 1];
            }
            position--;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public T remove(int index) {
        validateIndexOutOfRange(index);
        T removedValue = elements[index];

        for (int i = index; i < position - 1; i++) {
            elements[i] = elements[i + 1];
        }
        position--;

        return removedValue;
    }

    @Override
    public void clear() {
        elements = (T[]) new Object[size];
        size = DEFAULT_SIZE;
        position = 0;
    }

    private void checkCapacity() {
        if (position == size) {
            size = 2 + size + 1;
            elements = Arrays.copyOf(elements, size);
        }
    }

    private void validateIndexOutOfRange(int index) {
        if (index < 0 || index >= position) {
            throw new IndexOutOfBoundsException();
        }
    }
}
