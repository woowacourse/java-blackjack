package techcourse.generic.mission;

public class SimpleArrayList<T> implements SimpleList<T>{

    private static final int DEFAULT_CAPACITY = 10;

    private int size = 0;

    private T[] data;

    public SimpleArrayList(int firstCapacity) {
        if (firstCapacity > 0) {
            data = (T[]) new Object[firstCapacity];
        } else if (firstCapacity == 0) {
            data = (T[]) new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Invalid Capacity");
        }
    }

    public SimpleArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public static <T> SimpleArrayList<T> fromArrayToList(T[] objects) {
        SimpleArrayList<T> list = new SimpleArrayList<>();
        for (T object : objects) {
            list.add(object);
        }
        return list;
    }

    public SimpleArrayList(final T... values) {
        data = values;
        size = values.length;
    }

    public static <T extends Number> Double sum(SimpleArrayList<T> list) {
        Double sum = Double.valueOf(0);
        for (int i = 0; i < list.size; i++) {
            sum += list.get(i).doubleValue();
        }
        return sum;
    }

    @Override
    public boolean add(T value) {
        resize();
        data[size++] = value;
        return true;
    }

    private void resize() {
        if (size == data.length - 1) {
            T[] clone = data.clone();

            int length = data.length;
            data = (T[]) new Object[length * 2];
            for (int index = 0; index < length; index++) {
                data[index] = clone[index];
            }
        }
    }

    @Override
    public void add(int index, T value) {
        resize();
        int length = size - index;
        T[] clone = (T[])new Object[length];

        for (int i = index; i < index + length; i++) {
            clone[i - index] = data[i];
        }

        data[index] = value;
        for (int i = index + 1; i < index + 1 + clone.length ; i++) {
            data[i] = clone[i - (index + 1)];
        }
        size++;
    }

    @Override
    public T set(int index, T value) {
        T oldValue = data[index];
        data[index] = value;
        return oldValue;
    }

    @Override
    public T get(int index) {
        return data[index];
    }

    @Override
    public boolean contains(T value) {
        for (int index = 0; index < size; index++) {
            if (data[index].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int index = 0; index < size; index++) {
            if (data[index].equals(value)) {
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
        int index = -1;

        for (int dataIndex = 0; dataIndex < size; dataIndex++) {
            if (data[dataIndex].equals(value)) {
                index = dataIndex;
            }
        }
        if (index == -1) {
            return false;
        }
        moveForward(index);
        return true;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Invalid Index");
        }
        T oldValue = data[index];
        moveForward(index);
        return oldValue;
    }

    private void moveForward(int index) {
        if (size != 1) {
            T front;
            T back = data[size - 1];
            for (int backIndex = size - 1; backIndex > index; backIndex--) {
                front = data[backIndex - 1];
                data[backIndex - 1] = back;
                back = front;
            }
        }
        data[size - 1] = null;
        size--;
    }

    @Override
    public void clear() {
        for (int index = 0; index < size; index++) {
            data[index] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int index = 0; index < size; index++) {
            stringBuilder.append(data[index]);
            if (index != size - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
