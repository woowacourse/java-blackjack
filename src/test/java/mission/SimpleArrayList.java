package mission;

import java.util.Arrays;

public class SimpleArrayList<E> implements SimpleList<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private int size;
    private int capacity;
    private Object[] elements;

    public SimpleArrayList() {
        capacity = DEFAULT_CAPACITY;
        elements = new Object[DEFAULT_CAPACITY];
    }

    public SimpleArrayList(E... elements) {
        this.elements = Arrays.copyOf(elements, elements.length);
        this.size = elements.length;
        this.capacity = size;
    }


    @Override
    public boolean add(E value) {
        adjustCapacityIfNecessary();
        this.elements[size++] = value;

        return true;
    }

    @Override
    public void add(int index, E value) {
        validateIndex(index);
        size++;
        adjustCapacityIfNecessary();

        if (index > size) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = size; i >= index; i--) {
            this.elements[i + 1] = elements[i];
        }

        elements[index] = value;
    }

    private void adjustCapacityIfNecessary() {
        if (size >= capacity) {
            capacity += DEFAULT_CAPACITY;
            elements = Arrays.copyOf(elements, capacity);
        }
    }

    @Override
    public E set(int index, E value) {
        validateIndex(index);
        Object preElement = elements[index];
        this.elements[index] = value;

        return (E) preElement;
    }

    private void validateIndex(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E get(int index) {
        validateIndex(index);

        return (E) elements[index];
    }

    @Override
    public boolean contains(E value) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        if (size <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);

        if (index == -1) {
            return false;
        }

        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size--] = null;

        return true;
    }

    @Override
    public E remove(int index) {
        validateIndex(index);
        Object removedElement = elements[index];

        for (int i = index; i < size; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size--] = null;

        return (E) removedElement;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }

        size = 0;
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public String toString() {
        String str = "[";
        for (int i = 0; i < size; i++) {
            str += elements[i];
            if (i != size - 1) {
                str += ", ";
            }
        }
        str += "]";

        return str;
    }
}
