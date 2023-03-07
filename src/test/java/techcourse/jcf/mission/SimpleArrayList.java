package techcourse.jcf.mission;

import java.util.Arrays;
import java.util.Objects;

public class SimpleArrayList <E> implements SimpleList <E> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENT = {};

    private Object[] elements;
    private int size;

    public SimpleArrayList() {
        this.elements = EMPTY_ELEMENT;
        this.size = 0;
    }

    public SimpleArrayList(int initialSize) {
        if (initialSize < 0) {
            throw new IllegalArgumentException("배열의 크기는 음수가 될 수 없습니다.");
        }
        this.elements = new Object[initialSize];
        this.size = 0;
    }

    public SimpleArrayList(E[] array) {
        elements = Objects.requireNonNull(array);
        size = array.length;
    }

    @Override
    public boolean add(E value) {
        if (size == 0 || size == elements.length) {
            resize();
        }
        elements[size] = value;
        size++;
        return true;
    }

    @Override
    public void add(int index, E value) {
        checkIndex(index);

        if (index == size) {
            add(value);
            return;
        }

        if (size == elements.length) {
            resize();
        }

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = value;
        size++;
    }

    public void addLast(E value) {
        add(value);
    }

    public void addFirst(E value) {
        add(0, value);
    }

    @Override
    public E set(int index, E value) {
        checkIndex(index);
        elements[index] = value;
        return value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    @Override
    public boolean contains(E value) {
        return indexOfRange(value, 0, size) >= 0;
    }

    @Override
    public int indexOf(Object value) {
        return indexOfRange(value, 0, size);
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
        int removeIndex = indexOfRange(value, 0, size);

        if (removeIndex == -1) {
            return false;
        }

        remove(removeIndex);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        checkIndex(index);

        Object oldValue = elements[index];
        elements[index] = null;

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i+1];
            elements[i+1] = null;
        }
        size--;
        return (E) oldValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public void resize() {
        int capacity = elements.length;

        if (Arrays.equals(elements, EMPTY_ELEMENT)) {
            elements = new Object[DEFAULT_CAPACITY];
            return;
        }

        if (size == capacity) {
            int new_capacity = capacity * 2;
            elements = Arrays.copyOf(elements, new_capacity);
            return;
        }
    }

//    public E sum(SimpleList<E> simpleList) {
//        E sum;
//
//        for (int i = 0; i < simpleList.size(); i++) {
//            sum += simpleList.get(i);
//        }
//        return sum;
//    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("인덱스 범위를 확인하세요");
        }
    }

    private int indexOfRange(Object value, int start, int end) {
        Object[] copyElement = elements;
        if (value == null) {
            for (int i = start; i < end; i++) {
                if (copyElement[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (copyElement[i].equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
