package techcourse.jcf.mission;

import java.util.Objects;

public class SimpleArrayList<E> implements SimpleList<E> {
    private static int DEFAULT_CAPACITY = 10;

    private int size = 0;
    private int capacity;
    private Object[] elements;

    public SimpleArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public SimpleArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("초기 캐패시티는 양수여야 합니다.");
        }
        this.capacity = initialCapacity;
        this.elements = new Object[initialCapacity];
    }

    public SimpleArrayList(E... array) {
        elements = Objects.requireNonNull(array);
        size = array.length;
    }

    @Override
    public boolean add(E value) {
        if (size < capacity) {
            elements[size] = value;
        } else {
            capacity <<= 2;
            Object[] newElements = new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                newElements[i] = elements[i];
            }
            newElements[size] = value;
            this.elements = newElements;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("잘못된 index 입력입니다");
        }
        if (size == capacity) {
            capacity <<= 2;
        }
        Object[] newElements = new Object[capacity];
        for (int i = 0; i < index; i++) {
            newElements[i] = elements[i];
        }
        newElements[index] = value;
        for (int i = index; i < size; i++) {
            newElements[i] = elements[i];
        }
        this.elements = newElements;
        size++;
    }

    @Override
    public E set(int index, E value) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        E oldValue = (E) elements[index];
        elements[index] = value;
        return oldValue;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return (E) elements[index];
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(E value) {
        return indexOfRange(value, 0, size);
    }

    int indexOfRange(Object o, int start, int end) {
        Object[] es = elements;
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(es[i])) {
                    return i;
                }
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
        int index = indexOfRange(value, 0, size);
        if (index >= 0) {
            final Object[] es = elements;
            int newSize = size - 1;
            System.arraycopy(es, index + 1, es, index, newSize - index);
            es[size = newSize] = null;
            return true;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        final Object[] es = elements;
        E oldValue = (E) es[index];
        int newSize = size - 1;
        System.arraycopy(es, index + 1, es, index, newSize - index);
        es[size = newSize] = null;
        return oldValue;
    }

    @Override
    public void clear() {
        for (int to = size, i = size = 0; i < to; i++) {
            elements[i] = null;
        }
    }
}
