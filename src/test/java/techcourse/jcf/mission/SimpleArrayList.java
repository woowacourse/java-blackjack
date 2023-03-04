package techcourse.jcf.mission;

public class SimpleArrayList<T> implements SimpleList<T> {

    private int size;
    private int capacity = 10;
    private Object[] array;

    public SimpleArrayList() {
        this.array = new Object[capacity];
        this.size = 0;
    }

    @Override
    public boolean add(T value) {
        array = resize();
        array[size++] = value;
        return true;
    }

    @Override
    public void add(int index, T value) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        array = resize();
        size++;
        Object[] temp = new Object[capacity];
        for (int i = 0; i < index; i++) {
            temp[i] = array[i];
        }
        temp[index] = value;
        for (int i = index + 1; i < size; i++) {
            temp[i] = array[i - 1];
        }
        array = temp;
    }

    @Override
    public T set(int index, T value) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Object temp = array[index];
        array[index] = value;
        return (T) temp;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
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
    public boolean remove(T value) {
        Object[] temp = new Object[capacity];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                continue;
            }
            temp[j++] = array[i];
        }
        array = temp;
        size--;
        return true;
    }

    @Override
    public T remove(int index) {
        int j = 0;
        Object[] temp = new Object[capacity];
        T removed = null;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                removed = (T) array[index];
                continue;
            }
            temp[j++] = array[i];
        }
        size--;
        array = temp;
        return removed;
    }

    @Override
    public void clear() {
        array = new Object[capacity];
        size = 0;
    }

    private Object[] resize() {
        if (array.length < size + 1){
            Object[] temp = array;
            capacity *= 2;
            this.array = new Object[capacity];
            for (int i = 0; i < size; i++) {
                array[i] = temp[i];
            }
        }
        return array;
    }
}