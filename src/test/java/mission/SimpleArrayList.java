package mission;

public class SimpleArrayList<E> implements SimpleList<E> {

    private static final int INITIAL_CAPACITY = 10;

    private Object[] values = new Object[INITIAL_CAPACITY];
    private int size = 0;

    public SimpleArrayList(final E... elements) {
        for (final E element : elements) {
            add(element);
        }
    }

    @Override
    public boolean add(E value) {
        if (values.length == size) {
            resize();
        }
        values[size++] = value;
        return true;
    }

    private void resize() {
        final Object[] newValues = new Object[values.length + (values.length >> 1)];
        for (int i = 0; i < size; i++) {
            newValues[i] = values[i];
        }
        values = newValues;
    }

    @Override
    public void add(final int index, final E value) {
        if (values.length == size) {
            resize();
        }
        for (int i = index; i < size; i++) {
            values[i + 1] = values[i];
        }
        values[index] = value;
    }

    @Override
    public E set(final int index, final E value) {
        validateIndexBound(index);
        E returnValue = (E) values[index];
        values[index] = value;
        return returnValue;
    }

    @Override
    public boolean contains(E value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(final E value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public E get(int index) {
        validateIndexBound(index);
        return (E) values[index];
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
    public E remove(int index) {
        validateIndexBound(index);
        E returnValue = (E) values[index];
        pull(index);
        this.size--;
        return returnValue;
    }

    private void validateIndexBound(final int index) {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void pull(final int index) {
        for (int i = index; i < this.size; i++) {
            values[i] = values[i + 1];
        }
    }

    @Override
    public boolean remove(final E value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                pull(i);
                this.size--;
                return true;
            }
        }
        return false;
    }
}
