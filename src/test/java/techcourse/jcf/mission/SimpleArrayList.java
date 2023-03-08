package techcourse.jcf.mission;

public class SimpleArrayList implements SimpleList {

    private static final int INITIAL_CAPACITY = 10;

    private String[] array;
    private int size;
    private int capacity;

    public SimpleArrayList() {
        this.array = new String[INITIAL_CAPACITY];
        this.size = 0;
        this.capacity = INITIAL_CAPACITY;
    }

    public SimpleArrayList(final int initialCapacity) {
        this.array = new String[initialCapacity];
        this.size = 0;
        this.capacity = initialCapacity;
    }

    public SimpleArrayList(final String[] initialArray) {
        this.array = initialArray;
        this.size = initialArray.length;
        this.capacity = initialArray.length;
    }

    @Override
    public boolean add(final String value) {
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
        String[] newArray = new String[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public void add(final int index, final String value) {
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
    public String set(final int index, final String value) {
        checkValidIndex(index);

        String oldValue = array[index];
        array[index] = value;

        return oldValue;
    }

    @Override
    public String get(final int index) {
        checkValidIndex(index);
        return array[index];
    }

    @Override
    public boolean contains(final String value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(final String value) {
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
    public boolean remove(final String value) {
        if (indexOf(value) == -1) {
            return false;
        }
        remove(indexOf(value));
        return true;
    }

    @Override
    public String remove(final int index) {
        checkValidIndex(index);

        String oldValue = array[index];
        if (size - 1 > index) {
            System.arraycopy(array, index + 1, array, index, size - index);
        }
        array[size - 1] = null;

        size--;
        return oldValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }
}
