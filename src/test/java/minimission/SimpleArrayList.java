package minimission;

import java.util.Arrays;
import java.util.Objects;

public class SimpleArrayList implements SimpleList {

    private static final int DEFAULT_CAPACITY = 10;

    private String[] array;
    private int currentSize;

    public SimpleArrayList() {
        this.array = new String[DEFAULT_CAPACITY];
        currentSize = 0;
    }

    @Override
    public boolean add(final String value) {
        if (currentSize == array.length) {
            grow();
        }

        array[currentSize] = value;
        currentSize++;
        return true;
    }

    @Override
    public void add(final int index, final String value) {
        if (index < 0 || index > currentSize) {
            throw new IndexOutOfBoundsException("여기에는 값을 넣을 수 없습니다.");
        }

        if (currentSize == array.length) {
            grow();
        }

        System.arraycopy(array, index, array, index + 1, currentSize - index);

        array[index] = value;
        currentSize += 1;
    }

    private void grow() {
        int limitCapacity = currentSize + currentSize / 2;
        array = Arrays.copyOf(array, limitCapacity);
    }

    @Override
    public String set(final int index, final String value) {
        if (index < 0 || index > currentSize) {
            throw new IndexOutOfBoundsException("여기에는 값을 넣을 수 없습니다.");
        }

        array[index] = value;

        return value;
    }

    @Override
    public String get(final int index) {
        if (index < 0 || index > currentSize) {
            throw new IndexOutOfBoundsException("여기에는 값을 넣을 수 없습니다.");
        }

        return array[index];
    }

    @Override
    public boolean contains(final String value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(final String value) {
        for (int index = 0; index < array.length; index++) {
            if (value.equals(array[index])) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public boolean remove(final String value) {
        final int index = indexOf(value);

        if (index == -1) {
            return false;
        }

        remove(index);
        currentSize -= 1;

        return true;
    }

    @Override
    public String remove(final int index) {
        if (index < 0 || index > currentSize) {
            throw new IndexOutOfBoundsException("여기에는 값을 넣을 수 없습니다.");
        }

        final String oldValue = array[index];

        if (currentSize - 1 > index) {
            System.arraycopy(array, index +  1, array, index, currentSize - index);
        }

        array[currentSize] = null;
        currentSize -= 1;

        return oldValue;
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
    }
}
