package techcourse.jcf.mission;

public class SimpleArrayList<T> implements SimpleList<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int capacity;
    private int pointerToNext;
    private Object[] values;

    public SimpleArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        this.pointerToNext = 0;
        this.values = (T[]) new Object[10];
    }

    public SimpleArrayList(T... values) {
        this.capacity = DEFAULT_CAPACITY;
        this.values = values;
        this.pointerToNext = values.length;
        checkCapacityUnderKeepingOrder();
        checkCapacityOverKeepingOrder();
    }

    @Override
    public T get(int index) {
        checkIndexHaveValue(index);
        return (T) this.values[index];
    }

    @Override
    public boolean add(T value) {
        this.values[pointerToNext] = value;
        this.pointerToNext += 1;
        checkCapacityOverKeepingOrder();
        return true;
    }

    @Override
    public void add(int index, T value) {
        checkIndexHaveValue(index - 1);
        for (int i = pointerToNext; i > index; i--) {
            values[i] = values[i - 1];
        }
        this.values[index] = value;
        this.pointerToNext += 1;
        checkCapacityOverKeepingOrder();
    }

    @Override
    public T set(int index, T value) {
        checkIndexHaveValue(index);
        T valueBeforeChange = (T) this.values[index];
        this.values[index] = value;
        return valueBeforeChange;
    }


    @Override
    public boolean contains(T value) {
        for (int i = 0; i < this.pointerToNext; i++) {
            if (this.values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < this.pointerToNext; i++) {
            if (this.values[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.pointerToNext;
    }

    @Override
    public boolean isEmpty() {
        return this.pointerToNext == 0;
    }

    @Override
    public boolean remove(T value) {
        for (int i = 0; i < this.pointerToNext; i++) {
            if (this.values[i].equals(value)) {
                moveToLeftFromIndexToEnd(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
        checkIndexHaveValue(index);
        T valueToRemove = (T) this.values[index];
        moveToLeftFromIndexToEnd(index);
        return valueToRemove;
    }

    @Override
    public void clear() {
        this.capacity = DEFAULT_CAPACITY;
        this.pointerToNext = 0;
        this.values = (T[]) new Object[10];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.pointerToNext; i++) {
            sb.append(this.values[i]);
            sb.append(", ");
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        return sb.toString();
    }

    private void checkCapacityUnderKeepingOrder() {
        if (this.values.length < 10) {
            T[] newValues = (T[]) new Object[DEFAULT_CAPACITY];
            for (int i = 0; i < this.values.length; i++) {
                newValues[i] = (T) this.values[i];
            }
            this.values = newValues;
        }
    }

    private void checkCapacityOverKeepingOrder() {
        if (pointerToNext >= capacity * 0.8) {
            copyValuesIntactly();
        }
    }

    private void copyValuesIntactly() {
        this.capacity *= 2;
        T[] newValues = (T[]) new Object[this.capacity];
        for (int i = 0; i < this.values.length; i++) {
            newValues[i] = (T) this.values[i];
        }
        this.values = newValues;
    }

    private void checkIndexHaveValue(int index) {
        if (index >= this.pointerToNext) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void moveToLeftFromIndexToEnd(int index) {
        for (int i = index; i < this.pointerToNext; i++) {
            this.values[i] = this.values[i + 1];
        }
        this.pointerToNext -= 1;
    }
}
