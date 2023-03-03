package techcourse;

import java.util.Arrays;

public class SimpleArrayList implements SimpleList {

    private String[] elements = new String[1];
    private int count = 0;

    @Override
    public boolean add(final String value) {
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
    public void add(final int index, final String value) {
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
    public String set(final int index, final String value) {
        validateIndex(index);
        String old = elements[index];
        elements[index] = value;
        return old;
    }

    private void validateIndex(final int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException(String.format("index %d out of length %d", index, count));
        }
    }

    @Override
    public String get(final int index) {
        validateIndex(index);
        return elements[index];
    }

    @Override
    public boolean contains(final String value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(final String value) {
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
    public boolean remove(final String value) {
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
        count--;
        return true;
    }

    @Override
    public String remove(final int index) {
        validateIndex(index);
        String removed = elements[index];
        System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
        count--;
        return removed;
    }

    @Override
    public void clear() {
        count = 0;
        elements = new String[elements.length];
    }
}