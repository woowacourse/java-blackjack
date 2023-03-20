package techcourse.jcf.mission;

public class SimpleArrayList<E> implements SimpleList<E> {

    private static final int INITIAL_CAPACITY = 10;

    private Object[] array;
    private int size;
    private int capacity;

    public SimpleArrayList() {
        this.array = new Object[INITIAL_CAPACITY];
        this.size = 0;
        this.capacity = INITIAL_CAPACITY;
    }

    public SimpleArrayList(final E... values) {
        this.array = values;
        this.size = values.length;
        this.capacity = Math.max(values.length, INITIAL_CAPACITY);
    }


    public SimpleArrayList(final int initialCapacity) {
        this.array = new Object[initialCapacity];
        this.size = 0;
        this.capacity = initialCapacity;
    }

    @Override
    public boolean add(final E value) {
        checkMaxSize();
        array[size] = value;
        size++;
        return true;
    }

    private void checkMaxSize() {
        if (size == capacity) {
            grow();
        }
    }

    private void grow() {
        this.capacity += (capacity >> 1);
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public void add(final int index, final E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + "Size: " + size);
        }
        checkMaxSize();

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    private void checkValidIndex(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + "Size: " + size);
        }
    }

    @Override
    public E set(final int index, final E value) {
        checkValidIndex(index);

        Object oldValue = array[index];
        array[index] = value;

        return (E) oldValue;
    }

    @Override
    public E get(final int index) {
        checkValidIndex(index);
        return (E) array[index];
    }

    @Override
    public boolean contains(final E value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(final E value) {
        for (int i = 0; i < size; i++) {
            if (value == null && array[i] == null) {
                return i;
            }
            if (array[i].equals(value)) {
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
    public boolean remove(final E value) {
        if (indexOf(value) == -1) {
            return false;
        }
        remove(indexOf(value));
        return true;
    }

    @Override
    public E remove(final int index) {
        checkValidIndex(index);

        Object oldValue = array[index];
        if (size - 1 > index) {
            System.arraycopy(array, index + 1, array, index, size - index);
        }
        array[size - 1] = null;

        size--;
        return (E) oldValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }
}
