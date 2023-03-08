package study;

import java.util.Arrays;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] array = (T[])new Object[0];

    public SimpleArrayList(T... args) {
        for (T arg : args) {
            add(arg);
        }
    }

    @Override
    public boolean add(final T value) {
        T[] temp = (T[])new Object[array.length + 1];
        System.arraycopy(array, 0, temp, 0, array.length);
        temp[array.length] = value;
        array = temp;
        return true;
    }

    @Override
    public void add(final int index, final T value) {
        T[] temp = (T[])new Object[array.length + 1];
        System.arraycopy(array, 0, temp, 0, index);
        temp[index] = value;
        System.arraycopy(array, index + 1, temp, index + 1, array.length - index);
        array = temp;
    }

    @Override
    public T set(final int index, final T value) {
        T oldValue = array[index];
        array[index] = value;
        return oldValue;
    }

    @Override
    public T get(final int index) {
        return array[index];
    }

    @Override
    public boolean contains(final T value) {
        for (T t : array) {
            if (t.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(final T value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public boolean remove(final T value) {
        if (contains(value)) {
            T[] tmp = Arrays.copyOf(array, array.length - 1);
            int targetIndex = array.length;
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(value)) {
                    targetIndex = i;
                    continue;
                }

                if (i > targetIndex) {
                    tmp[i - 1] = array[i];
                    continue;
                }
                tmp[i] = array[i];
            }
            return true;
        }
        return false;
    }

    @Override
    public T remove(final int removeIndex) {
        T old = array[removeIndex];
        T[] tmp = (T[])new Object[array.length - 1];
        System.arraycopy(array, 0, tmp, 0, removeIndex);
        System.arraycopy(array, removeIndex + 1, tmp, removeIndex, array.length - removeIndex);
        return old;
    }

    @Override
    public void clear() {
        array = (T[])new Object[0];
    }
}
