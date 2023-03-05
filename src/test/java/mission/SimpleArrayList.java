package mission;

import java.util.Arrays;

public class SimpleArrayList<E> implements SimpleList<E> {
    private static final int INITIAL_SIZE = 0;
    private E[] elementData;
    private int size;

    public SimpleArrayList(final E... elements) {
        this.elementData = (E[]) new Object[INITIAL_SIZE];
        int i=0;
        for (final E element : elements) {
            add(element);
        }
        size = elementData.length;
    }


    public void checkWrongIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("잘못된 인덱스 참조");
        }
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
            checkWrongIndex(index);
        }
        E[] targetData = (E[]) new Object[++size];
        System.arraycopy(elementData, 0, targetData, 0, index);
        targetData[index] = value;
        System.arraycopy(elementData, index, targetData, index + 1, size - index - 1);
        elementData = targetData;
    }

    @Override
    public E set(int index, E value) {
        checkWrongIndex(index);
        E oldValue = elementData[index];
        elementData[index] = value;
        return oldValue;
    }

    @Override
    public E get(int index) {
        checkWrongIndex(index);
        return elementData[index];
    }

    @Override
    public boolean contains(E value) {
        return Arrays.stream(elementData).anyMatch(element -> element.equals(value));
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
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);
        if (index < 0) {
            return false;
        }

        E[] targetData = (E[]) new Object[--size];
        System.arraycopy(elementData, 0, targetData, 0, index);
        System.arraycopy(elementData, index + 1, targetData, index, size - index);
        elementData = targetData;
        return true;
    }

    @Override
    public E remove(int index) {
        checkWrongIndex(index);
        E oldValue = elementData[index];
        E[] targetData = (E[]) new Object[--size];
        System.arraycopy(elementData, 0, targetData, 0, index);
        System.arraycopy(elementData, index + 1, targetData, index, size - index);
        elementData = targetData;
        return oldValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

}
