package techcourse.jcf.mission;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class SimpleArrayList<E> implements SimpleList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENT_DATA = {};

    private Object[] elementData;
    private int size;

    public SimpleArrayList() {
        this.elementData = EMPTY_ELEMENT_DATA;
    }

    public SimpleArrayList(E[] elementData) {
        this.elementData = elementData;
        this.size = elementData.length;
    }

    public SimpleArrayList(E e1, E e2) {
        this.elementData = new Object[]{e1, e2};
        this.size = elementData.length;
    }

    public SimpleArrayList(E e1, E e2, E e3) {
        this.elementData = new Object[]{e1, e2, e3};
        this.size = elementData.length;
    }

    @Override
    public boolean add(E value) {
        resizeCapacity(size + 1);
        elementData[size++] = value;
        return true;
    }

    @Override
    public void add(int index, E value) {
        validateIndexRange(index);
        resizeCapacity(size + 1);
        fastAdd(index);
        elementData[index] = value;
        size++;
    }

    private void resizeCapacity(int minCapacity) {
        int newCapacity = computeCapacity(elementData, minCapacity);
        if (newCapacity > elementData.length) {
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private int computeCapacity(Object[] elementData, int minCapacity) {
        if (elementData == EMPTY_ELEMENT_DATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    private void fastAdd(int index) {
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
    }

    @Override
    public E set(int index, E value) {
        validateIndexRange(index);
        E previous = (E) elementData[index];
        elementData[index] = value;
        return previous;
    }

    @Override
    public E get(int index) {
        validateIndexRange(index);
        return (E) elementData[index];
    }

    @Override
    public boolean contains(E value) {
        return Arrays.asList(elementData).contains(value);
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], value)) {
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
        int index = indexOf(value);
        if (index > -1) {
            fastRemove(index);
            return true;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        validateIndexRange(index);
        E oldValue = (E) elementData[index];
        fastRemove(index);
        return oldValue;
    }

    private void fastRemove(int index) {
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[--size] = null;
    }

    @Override
    public void clear() {
        elementData = EMPTY_ELEMENT_DATA;
        size = 0;
    }

    private void validateIndexRange(int index) {
        if (index < 0 || index + 1 > size) {
            throw new IndexOutOfBoundsException("인덱스의 범위가 올바르지 않습니다. index: " + index);
        }
    }

    @Override
    public String toString() {
        return Arrays.stream(elementData)
                .filter(Objects::nonNull)
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
