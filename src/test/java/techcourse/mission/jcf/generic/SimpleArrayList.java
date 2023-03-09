package techcourse.mission.jcf.generic;

import java.util.Arrays;

public class SimpleArrayList<E> implements SimpleList<E> {

    public static final int DEFAULT_SIZE = 0;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private Object[] elementData;
    private int size;

    public SimpleArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        this.size = DEFAULT_SIZE;
    }

    public SimpleArrayList(final Object... elementData) {
        this.elementData = elementData;
        this.size = elementData.length;
    }

    @Override
    public boolean add(E value) {
        elementData = Arrays.copyOf(elementData, ++size);
        elementData[size - 1] = value;
        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index != size) {
            checkIndex(index);
        }
        Object[] target = new Object[++size];
        System.arraycopy(elementData, 0, target, 0, index);
        target[index] = value;
        System.arraycopy(elementData, index, target, index + 1, size - index - 1);
        elementData = target;
    }

    @Override
    public E set(int index, E value) {
        checkIndex(index);
        E oldValue = elementData(index);
        elementData[index] = value;
        return oldValue;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return elementData(index);
    }

    @Override
    public boolean contains(E value) {
        for (Object data : elementData) {
            if (data.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(E value) {
        for (int index = 0; index < size; index++) {
            if (elementData[index].equals(value)) {
                return index;
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
        if (index == 1) {
            return false;
        }
        removeData(index);
        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E oldData = elementData(index);
        removeData(index);
        return oldData;
    }

    @Override
    public void clear() {
        for (int index = 0; index < size; index++) {
            elementData[index] = null;
        }
        size = 0;
    }

    @Override
    public final <T> SimpleList<T> fromArrayToList(final T[] values) {
        SimpleList<T> list = new SimpleArrayList<>();
        for (T value : values) {
            list.add(value);
        }
        return list;
    }

    private void removeData(int index) {
        Object[] target = new Object[--size];
        System.arraycopy(elementData, 0, target, 0, index);
        System.arraycopy(elementData, index + 1, target, index, size - index);
        elementData = target;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }
}
