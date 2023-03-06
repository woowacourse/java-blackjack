package techcourse.jcf.mission;

import java.util.Arrays;

public class SimpleArrayList<E> implements SimpleList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[] elementData;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public SimpleArrayList() {
        this.elementData = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SafeVarargs
    public SimpleArrayList(final E... values) {
        this();
        for (final E value : values) {
            add(value);
        }
    }

    @Override
    public boolean add(E value) {
        if (size == elementData.length) {
            elementData = grow();
            elementData[size++] = value;
        } else elementData[size++] = (E) value;
        return true;
    }

    @Override
    public void add(int index, E value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        for (int i = size - 1; i >= index; i--) {
            set(i + 1, get(i));
        }
        size++;
        set(index, value);
    }

    private E[] grow() {
        return Arrays.copyOf(elementData, size * 2);
    }

    @Override
    public E set(int index, E value) {
        elementData[index] = value;
        return value;
    }

    @Override
    public E get(int index) {
        return elementData[index];
    }

    @Override
    public boolean contains(E value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(value)) {
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
    public boolean remove(E value) {
        remove(indexOf(value));
        return true;
    }

    @Override
    public E remove(int index) {
        E value = elementData[index];
        for (int i = indexOf(value); i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
        return value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        Arrays.fill(elementData, null);
        this.elementData = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
}
