package techcourse.jcf.mission;

import java.util.Objects;

public class SimpleArrayList<E> implements SimpleList<E> {

    private E[] arr;
    private int capacity;
    private int size;

    public SimpleArrayList() {
        this.capacity = 1;
        this.size = 0;
        this.arr = (E[]) new Object[capacity];
    }

    public static <E> SimpleArrayList<E> fromArrayToList(E[] array) {
        SimpleArrayList<E> simpleArrayList = new SimpleArrayList<>();
        for (E value : array) {
            simpleArrayList.add(value);
        }
        return simpleArrayList;
    }

    public static <E extends Number> double sum(SimpleArrayList<E> numbers) {
        double sum = 0;
        for (int i = 0; i < numbers.size; i++) {
            sum += numbers.get(i).doubleValue();
        }
        return sum;
    }

    public static <E extends Number> SimpleArrayList<E> filterNegative(SimpleArrayList<? extends E> numbers) {
        SimpleArrayList<E> newSimpleArrayList = new SimpleArrayList<>();
        for (int i = 0; i < numbers.size; i++) {
            E number = numbers.get(i);
            double doubleValue = number.doubleValue();
            if (doubleValue >= 0) {
                newSimpleArrayList.add(number);
            }
        }
        return newSimpleArrayList;
    }

    @Override
    public boolean add(E value) {
        expandArr();

        arr[size] = value;
        size += 1;
        return true;
    }

    private void expandArr() {
        if (isFull()) {
            capacity *= 2;
            arr = (E[]) createdExpandedArr();
        }
    }

    private boolean isFull() {
        return size == capacity;
    }

    private Object[] createdExpandedArr() {
        Object[] newArr = new Object[capacity];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    @Override
    public void add(int index, E value) {
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
    public E set(int index, E value) {
        E oldValue = arr[index];
        arr[index] = value;
        return oldValue;
    }

    @Override
    public E get(int index) {
        validateIndex(index);
        return arr[index];
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(E value) {
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
    public boolean remove(E value) {
        int index = indexOf(value);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        E removedValue = arr[index];

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
        this.arr = (E[]) new Object[capacity];
    }
}
