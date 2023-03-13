package techcourse.jcf.mission;

public class SimpleArrayList<T> implements SimpleList<T> {


    private static final int INITIAL_SIZE = 10;
    private int size;
    private T[] stores;

    public SimpleArrayList() {
        size = 0;
        this.stores = (T[]) new Object[INITIAL_SIZE];
    }

    public SimpleArrayList(T... value) {
        size = value.length;
        this.stores = value;
    }


    public static <T> SimpleList fromArrayToList(final T[] values) {
        final SimpleArrayList<Object> arrayList = new SimpleArrayList<>();
        for (int i = 0; i < values.length; i++) {
            arrayList.add(values[i]);
        }
        return arrayList;
    }

    @Override
    public boolean add(final T value) {
        stores[size] = value;
        size++;
        return true;
    }

    @Override
    public void add(final int index, final T value) {
        isInRange(index);

        if (index == size) {
            add(value);
            return;
        }
        for (int i = size; i > index; i--) {
            stores[i] = stores[i - 1];
        }
        stores[index] = value;
        size++;
    }

    @Override
    public T set(final int index, final T value) {
        isInRange(index);
        final Object oldValue = stores[index];
        stores[index] = value;
        return (T) oldValue;
    }


    @Override
    public T get(final int index) {
        isInRange(index);
        return stores[index];
    }

    @Override
    public boolean contains(final T value) {
        if (indexOf(value) >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(final T value) {
        int i;

        for (i = 0; i < size; i++) {
            if (stores[i].equals(value)) {
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
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public T remove(final int index) {
        isInRange(index);
        T oldValue = stores[index];
        stores[index] = null;

        for (int i = index; i < size - 1; i++) {
            stores[i] = stores[i + 1];
            stores[i + 1] = null;
        }
        size--;
        return null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            stores[i] = null;
        }
        size = 0;
    }

    private void isInRange(final int index) {
        if (size < index || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }
}
