package jcf;

import java.util.Arrays;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {
    private Object[] array;
    private int size;

    public SimpleArrayList() {
//        this.array = new T[10];
//        new T()는 불가능하다. compile 할 때 T가 생성자를 가지는지(private 생성자일 수 있기에) 알 수 없기 때문이다.
        array = new Object[10];
        size = 0;
    }

    @Override
    public boolean add(T value) {
        enlargeCapacityIfExcessiveSize();
        array[size++] = value;
        return true;
    }

    @Override
    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        enlargeCapacityIfExcessiveSize();
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        size += 1;
        set(index, value);
    }

    private void enlargeCapacityIfExcessiveSize() {
        if (size == array.length) {
            array = Arrays.copyOf(array, size * 2);
        }
    }

    @Override
    public T set(int index, T value) {
        checkIndexInRange(index);
        Object ret = array[index];
        array[index] = value;
        return (T) ret;
    }

    @Override
    public T get(int index) {
        checkIndexInRange(index);
        return (T) array[index];
    }

    @Override
    public boolean contains(T value) {
        for (Object obj : array) {
            if (Objects.equals(obj, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, array[i])) {
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
    public boolean remove(T value) {
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public T remove(int index) {
        checkIndexInRange(index);
        Object ret = array[index];
        for (int i = index + 1; i < size; i++) {
            array[i - 1] = array[i];
        }
        size -= 1;
        return (T) ret;
    }

    private void checkIndexInRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void clear() {
        size = 0;
    }
}
