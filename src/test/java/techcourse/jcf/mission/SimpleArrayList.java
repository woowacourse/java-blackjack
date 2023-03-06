package techcourse.jcf.mission;

public class SimpleArrayList<T> implements SimpleList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] values;
    private int size;

    public SimpleArrayList() {
        this.values = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @SafeVarargs
    public SimpleArrayList(T... parameters) {
        this.values = new Object[DEFAULT_CAPACITY];
        for (T parameter : parameters) {
            add(parameter);
        }
        this.size = parameters.length;
    }

    @Override
    public boolean add(T value) {
        if (size >= values.length) {
            increaseCapacity();
        }
        values[size++] = value;
        return true;
    }

    private void increaseCapacity() {
        Object[] copyValues = new String[size * 2];
        System.arraycopy(values, 0, copyValues, 0, values.length);
        this.values = copyValues;
    }

    @Override
    public void add(int index, T value) {
        checkRange(index);
        if (size >= values.length) {
            increaseCapacity();
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    private void checkRange(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public T set(int index, T value) {
        checkRange(index);
        T oldValue = (T) values[index];
        values[index] = value;
        return oldValue;
    }

    @Override
    public T get(int index) {
        checkRange(index);
        return (T) values[index];
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
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
    public boolean remove(T value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                forwardItems(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        T oldValue = (T) values[index];
        forwardItems(index);
        return oldValue;
    }

    private void forwardItems(int index) {
        for (; index < size - 1; index++) {
            values[index] = values[index + 1];
        }
        values[--size] = null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            values[i] = null;
        }
        size = 0;
    }
}
