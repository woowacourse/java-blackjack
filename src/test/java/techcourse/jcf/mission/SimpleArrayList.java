package techcourse.jcf.mission;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleArrayList<E> implements SimpleList<E> {


    public static final int INITIAL_CAPACITY = 20;
    private int CAPACITY;

    private Object[] array;
    private int size = 0;

    public SimpleArrayList() {
        CAPACITY = INITIAL_CAPACITY;
        this.array = new Object[CAPACITY];
    }

    public SimpleArrayList(int initSize) {
        CAPACITY = initSize;
        this.array = new Object[CAPACITY];
    }

    public SimpleArrayList(E value1, E value2, E... values) {
        size = values.length + 2;
        CAPACITY = size + INITIAL_CAPACITY;
        array = new Object[CAPACITY];
        array[0] = value1;
        array[1] = value2;
        System.arraycopy(values, 0, array, 2, values.length);
    }

    @Override
    public boolean add(E value) {
        growCapacity();
        array[size++] = value;
        return true;
    }

    private void growCapacity() {
        if (size == array.length) {
            CAPACITY *= 2;
            array = Arrays.copyOf(array, CAPACITY);
        }
    }

    @Override
    public void add(int index, E value) {
        growCapacity();
        System.arraycopy(array, index,
            array, index + 1,
            size - index);
        array[index] = value;
        size = size + 1;
    }

    @Override
    public E set(int index, E value) {
        E oldValue = (E) array[index];
        array[index] = value;
        return oldValue;
    }

    @Override
    public E get(int index) {
        return (E) array[index];
    }

    @Override
    public boolean contains(E value) {
        for (Object o : array) {
            if (o.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
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
        int findIndex = indexOf(value);
        if (findIndex == -1) {
            return false;
        }
        remove(findIndex);
        return true;
    }

    @Override
    public E remove(int index) {
        E removeValue = (E) array[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }
        size--;
        return removeValue;
    }

    @Override
    public void clear() {
        array = new String[INITIAL_CAPACITY];
        CAPACITY = INITIAL_CAPACITY;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return (Iterator<E>) Arrays.stream(array).iterator();
    }
}
