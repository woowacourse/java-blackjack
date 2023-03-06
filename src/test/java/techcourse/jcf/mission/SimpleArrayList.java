package techcourse.jcf.mission;

import java.util.Objects;

public class SimpleArrayList implements SimpleList {

    private String[] arr;
    private int capacity;
    private int size;

    public SimpleArrayList() {
        this.capacity = 1;
        this.size = 0;
        this.arr = new String[capacity];
    }

    @Override
    public boolean add(String value) {
        expandArr();

        arr[size] = value;
        size += 1;
        return true;
    }

    private void expandArr() {
        if (isFull()) {
            capacity *= 2;
            arr = createdExpandedArr();
        }
    }

    private boolean isFull() {
        return size == capacity;
    }

    private String[] createdExpandedArr() {
        String[] newArr = new String[capacity];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }


    @Override
    public void add(int index, String value) {
        validateIndex(index);
        expandArr();
        for (int i = size; i < index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = value;
        size += 1;
    }

    private void validateIndex(int index) {
        if (index >= size) {
            String message = String.format("Index : %d , Size : %d", index, size);
            throw new ArrayIndexOutOfBoundsException(message);
        }
    }

    @Override
    public String set(int index, String value) {
        String oldValue = arr[index];
        arr[index] = value;
        return oldValue;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return arr[index];
    }

    @Override
    public boolean contains(String value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(String value) {
        for (int i = 0; i < arr.length; i++) {
            if (Objects.equals(arr[i], value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(String value) {
        int index = indexOf(value);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public String remove(int index) {
        String removedValue = arr[index];

        for (int i = index; i < size; i++) {
            arr[i] = arr[i + 1];
        }
        size -= 1;
        arr[size] = null;

        return removedValue;
    }

    @Override
    public void clear() {
        this.capacity = 1;
        this.size = 0;
        this.arr = new String[capacity];
    }
}
