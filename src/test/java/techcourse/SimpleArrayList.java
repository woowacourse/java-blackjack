package techcourse;

import java.util.Arrays;

public class SimpleArrayList<T> implements SimpleList<T> {

    private Object[] elements = new Object[1];
    private int count = 0;

    public SimpleArrayList() {
    }

    @SafeVarargs
    public SimpleArrayList(final T... values) {
        this.elements = values;
        count = values.length;
    }

    @Override
    public boolean add(final T value) {
        if (isFull()) {
            grow();
        }
        elements[count++] = value;
        return true;
    }

    private void grow() {
        elements = Arrays.copyOf(elements, elements.length * 2);
    }

    @Override
    public void add(final int index, final T value) {
        validateAddIndex(index);
        if (isFull()) {
            grow();
        }
        count++;
        System.arraycopy(elements, index, elements, index + 1, elements.length - index - 1);
        elements[index] = value;
    }

    private void validateAddIndex(final int index) {
        if (count < index || index < 0) {
            throw new IndexOutOfBoundsException(String.format("index %d out of length %d", index, count));
        }
    }

    private boolean isFull() {
        return count == elements.length;
    }

    @Override
    public T set(final int index, final T value) {
        validateIndex(index);
        T old = (T) elements[index];
        elements[index] = value;
        return old;
    }

    private void validateIndex(final int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException(String.format("index %d out of length %d", index, count));
        }
    }

    @Override
    public T get(final int index) {
        validateIndex(index);
        return (T) elements[index];
    }

    @Override
    public boolean contains(final T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(final T value) {
        for (int i = 0; i < count; i++) {
            if (elements[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean remove(final T value) {
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
        count--;
        return true;
    }

    @Override
    public T remove(final int index) {
        validateIndex(index);
        T removed = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
        count--;
        return removed;
    }

    @Override
    public void clear() {
        count = 0;
        elements = new Object[elements.length];
    }
}
