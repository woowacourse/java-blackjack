package mini;

import java.util.Arrays;

public class SimpleArrayList<T> implements SimpleList<T> {
    private Object[] array = new Object[10];
    private int endIndex = 0;
    private int capacity = 10;

    public SimpleArrayList() {
    }

    public SimpleArrayList(T... numbers) {
        for (T n : numbers) {
            this.add(n);
        }
    }

    @Override
    public boolean add(T value) {
        if (endIndex < capacity) {
            array[endIndex++] = value;
            return true;
        }

        addCapacity();
        return add(value);
    }

    private void addCapacity() {
        if (((long) this.capacity << 1) > Integer.MAX_VALUE) {
            throw new OutOfMemoryError();
        }

        int newCapacity = capacity << 1;
        Object[] newArray = new Object[newCapacity];

        for (int i = 0; i < endIndex; i++) {
            newArray[i] = array[i];
        }

        this.array = newArray;
        this.capacity = newCapacity;
    }

    @Override
    public void add(int index, T value) {
        validateIndex(index);

        if (endIndex == capacity) {
            addCapacity();
        }

        Object[] copy = copyOf(index);

        array[index] = value;
        endIndex++;
        for (int i = index + 1; i < endIndex; i++) {
            array[i] = copy[i - index - 1];
        }
    }

    private Object[] copyOf(int startIndex) {
        Object[] copyOf = new Object[capacity - startIndex];
        for (int i = startIndex; i < endIndex; i++) {
            copyOf[i - startIndex] = array[i];
        }

        return copyOf;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= endIndex) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T set(int index, T value) {
        validateIndex(index);

        Object previous = array[index];
        array[index] = value;

        return (T) previous;
    }

    @Override
    public T get(int index) {
        validateIndex(index);

        return (T) array[index];
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < endIndex; i++) {
            if (array[i].equals(value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < endIndex; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int size() {
        return endIndex;
    }

    @Override
    public boolean isEmpty() {
        return endIndex == 0;
    }

    @Override
    public boolean remove(T value) {
        int index = -1;
        for (int i = 0; i < endIndex; i++) {
            if (array[i].equals(value)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return false;
        }

        array[index] = null;
        Object[] copy = copyOf(index + 1);
        endIndex--;
        for (int i = index; i < endIndex; i++) {
            array[i] = copy[i - index];
        }

        return true;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);

        Object previous = array[index];

        Object[] copy = copyOf(index + 1);
        array[index] = null;
        endIndex--;
        for (int i = index; i < endIndex; i++) {
            array[i] = copy[i - index];
        }

        return (T) previous;
    }

    @Override
    public void clear() {
        array = new Object[10];
        endIndex = 0;
        capacity = 10;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, endIndex));
    }
}