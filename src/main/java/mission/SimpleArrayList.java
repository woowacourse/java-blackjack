package mission;

public class SimpleArrayList<T> implements SimpleList<T> {

    private static final int DEFAULT_SIZE = 5;

    private Object[] arrayList;
    private int size = 0;

    public SimpleArrayList() {
        this.arrayList = new Object[DEFAULT_SIZE];
    }

    @Override
    public boolean add(final T value) {
        if (size == arrayList.length) {
            Object[] newList = new Object[DEFAULT_SIZE + size];
            System.arraycopy(arrayList, 0, newList, 0, size);
            arrayList = newList;
        }
        arrayList[size] = value;
        size++;

        return true;
    }

    @Override
    public void add(int index, T value) {
        if (size >= arrayList.length) {
            Object[] newList = new Object[DEFAULT_SIZE + size];
            System.arraycopy(arrayList, 0, newList, 0, size);
            newList[index] = value;
            arrayList = newList;
        }

        if (size < arrayList.length) {
            for (int i = size - 1; i >= index; i--) {
                arrayList[i + 1] = arrayList[i];
            }
            arrayList[index] = value;
        }

        size++;
    }

    @Override
    public boolean contains(T value) {
        for (int index = 0; index < size; index++) {
            if (arrayList[index].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int index = 0; index < size; index++) {
            if (arrayList[index].equals(value)) {
                return index;
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
        if (contains(value)) {
            int index = indexOf(value);
            remove(index);

            return true;
        }
        return false;
    }

    @Override
    public T remove(int index) {
        T removeValue = get(index);

        for (int i = index; i < size - 1; i++) {
            arrayList[i] = arrayList[i + 1];
        }
        size--;

        return removeValue;
    }

    @Override
    public void clear() {
        arrayList = new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public T set(int index, T value) {
        validateIndex(index);
        arrayList[index] = value;

        return value;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return (T) arrayList[index];
    }

    private void validateIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("[ERROR] 인덱스가 범위를 벗어났습니다.");
        }
    }
}
