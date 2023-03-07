package techcourse.jcf.mission;

import static java.text.MessageFormat.format;

import java.util.Arrays;

public class SimpleArrayList<T> implements SimpleList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] values;
    private int size;

    public SimpleArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public SimpleArrayList(final int capacity) {
        validateCapacity(capacity);
        initCapacity(capacity);
        this.size = 0;
    }

    private void validateCapacity(final int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(format("용량은 음수가 될 수 없습니다. 입력: {0}", capacity));
        }
    }

    private void initCapacity(final int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            this.values = new Object[DEFAULT_CAPACITY];
        } else {
            this.values = new Object[capacity];
        }
    }

    @Override
    public boolean add(final T value) {
        if (size == values.length) {
            values = expand();
        }
        addElement(size, value);
        return true;
    }

    private Object[] expand() {
        final int expandCapacity = calculateExpandCapacity(values.length);
        return Arrays.copyOf(values, expandCapacity);
    }

    private int calculateExpandCapacity(final int capacity) {
        return capacity + (int) (capacity * 0.5);
    }

    private void addElement(final int index, final T value) {
        values[index] = value;
        size++;
    }

    @Override
    public void add(final int index, final T value) {
        validateIndexForAdd(index);
        if (size == values.length) {
            values = expand();
        }
        shiftRight(index, size - 1);
        addElement(index, value);
    }

    private void validateIndexForAdd(final int index) {
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException(
                    format("해당 인덱스에서 작업을 수행할 수 없습니다. 인덱스: {0}, 크기: {1}", index, size));
        }
    }

    private void shiftRight(final int startIndex, final int endIndex) {
        for (int i = endIndex; i >= startIndex; i--) {
            values[i + 1] = values[i];
        }
    }

    @Override
    public T set(final int index, final T value) {
        validateIndex(index);
        final T oldValue = (T) values[index];
        values[index] = value;
        return oldValue;
    }

    private void validateIndex(final int index) {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException(
                    format("해당 인덱스에서 작업을 수행할 수 없습니다. 인덱스: {0}, 크기: {1}", index, size));
        }
    }

    @Override
    public T get(final int index) {
        validateIndex(index);
        return (T) values[index];
    }

    @Override
    public boolean contains(final T value) {
        final int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        return true;
    }

    @Override
    public int indexOf(final T value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
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
    public boolean remove(final T value) {
        final int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        shiftLeft(index + 1, size - 1);
        size--;
        return true;
    }

    private void shiftLeft(final int startIndex, final int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            values[i - 1] = values[i];
        }
        values[endIndex] = null;
    }

    @Override
    public T remove(final int index) {
        validateIndex(index);
        final T value = (T) values[index];
        shiftLeft(index + 1, size - 1);
        size--;
        return value;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            values[i] = null;
        }
        size = 0;
    }
}
