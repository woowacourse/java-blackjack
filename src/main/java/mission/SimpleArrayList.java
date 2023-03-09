package mission;

import java.util.Arrays;
import java.util.Collection;

public class SimpleArrayList<TYPE> implements SimpleList<TYPE> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = {};

    transient Object[] elementData; // non-private to simplify nested class access
    private int size;

    public SimpleArrayList(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Initial Capacity should be positive number.");
        }
    }

    public SimpleArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    public SimpleArrayList(Collection<? extends TYPE> c) {
        size = c.size();
        if (size != 0) {
            elementData = Arrays.copyOf(c.toArray(), size, Object[].class);
        } else {
            elementData = EMPTY_ELEMENTDATA;
        }
    }

    public SimpleArrayList(TYPE... values) {
        size = values.length;
        if (size != 0) {
            elementData = Arrays.copyOf(values, size, Object[].class);
        } else {
            elementData = EMPTY_ELEMENTDATA;
        }
    }


    private Object[] grow(int curLength) {
        int newCapacity = Math.max(curLength << 1, DEFAULT_CAPACITY);
        return elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public boolean add(TYPE value) {
        final int s = size;
        if (s == elementData.length) {
            elementData = grow(s);
        }
        elementData[s] = value;
        size = s + 1;

        return true;
    }

    @Override
    public void add(int index, TYPE value) {
        checkRange(index);
        final int s = size;
        if (s == elementData.length) {
            elementData = grow(s);
        }

        System.arraycopy(elementData, index,
                elementData, index + 1,
                s - index);
        elementData[index] = elementData;

        size = s + 1;
    }

    private void checkRange(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("잘못된 인덱스 입니다.");
        }
    }

    @Override
    public TYPE set(int index, TYPE value) {
        checkRange(index);
        TYPE oldValue = (TYPE) elementData[index];

        elementData[index] = value;
        return oldValue;
    }

    @Override
    public TYPE get(int index) {
        checkRange(index);
        return (TYPE) elementData[index];
    }

    @Override
    public boolean contains(TYPE value) {
        for (Object element : elementData) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(TYPE value) {
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
    public boolean remove(TYPE value) {
        final int index = indexOf(value);
        if (index < 0) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public TYPE remove(int index) {
        final int s = size - 1;
        final TYPE removedValue = (TYPE) elementData[index];

        System.arraycopy(elementData, index + 1,
                elementData, index,
                s - index);
        elementData[s] = null;
        size = s;

        return removedValue;
    }

    @Override
    public void clear() {
        final Object[] es = elementData;
        for (int i = 0; i < size; i++) {
            es[i] = null;
        }
        size = 0;
    }


}
