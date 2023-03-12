package generic;

import java.util.StringJoiner;

public class SimpleArrayList<E> implements SimpleList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] store;
    private int size = 0;

    public SimpleArrayList() {
        store = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean add(final Object value) {
        if (store.length <= size) {
            increaseCapacity();
        }
        store[size] = value;
        size++;
        return true;
    }

    private void increaseCapacity() {
        Object[] copy = store.clone();
        store = new String[size + DEFAULT_CAPACITY];
        this.setAll(copy, store);
    }

    private void setAll(final Object[] copy, final Object[] origin) {
        for (int i = 0; i < copy.length; i++) {
            origin[i] = copy[i];
        }
    }

    @Override
    public void add(final int index, final E value) {
        if (store.length <= size) {
            increaseCapacity();
        }
        pushBackElements(index);
        store[index] = value;
        size++;
    }

    private void pushBackElements(final int index) {
        for (int i = index; i < store.length; i++) {
            store[i + 1] = store[i];
        }
    }

    @Override
    public E set(final int index, final E value) {
        store[index] = value;
        return value;
    }

    @Override
    public E get(final int index) {
        return (E) store[index];
    }

    @Override
    public boolean contains(final E value) {
        for (int i = 0; i < size; i++) {
            if (store[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(final E value) {
        for (int i = 0; i < size; i++) {
            if (store[i].equals(value)) {
                return i;
            }
        }
        return 0;
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
    public boolean remove(final E value) {
        for (int i = 0; i < size; i++) {
            if (store[i].equals(value)) {
                pushFrontElement(i);
                size--;
                return true;
            }
        }
        return false;
    }

    private void pushFrontElement(final int index) {
        for (int i = size - 1; i > index; i--) {
            store[i - 1] = store[i];
        }
        store[size - 1] = null;
    }

    @Override
    public E remove(final int index) {
        for (int i = 0; i < size; i++) {
            if (i == index) {
                E removed = (E) store[index];
                pushFrontElement(i);
                size--;
                return removed;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        store = new String[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public String toString() {
        return "SimpleArrayList{" +
                "store=" + toSimpleArrayString() +
                ", size=" + size +
                '}';
    }

    private String toSimpleArrayString() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(store[i].toString());
        }

        return "[" + stringJoiner.toString() + "]";
    }
}
