package list;

public class SimpleArrayList implements SimpleList {

    private static final int INITIAL_CAPACITY = 1;
    private static final int INITIAL_SIZE = 0;

    private int capacity;
    private int size;
    private String[] data;

    public SimpleArrayList() {
        this.capacity = INITIAL_CAPACITY;
        this.size = INITIAL_SIZE;
        this.data = new String[capacity];
    }

    @Override
    public boolean add(String value) {
        if (capacity == size) {
            expandCapacity();
        }
        data[size++] = value;
        return true;
    }

    @Override
    public void add(int index, String value) {
        checkIndexOutOfBounds(index);
        if (capacity == size) {
            expandCapacity();
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        size++;
    }

    @Override
    public String set(int index, String value) {
        checkIndexOutOfBounds(index);
        data[index] = value;
        return value;
    }

    @Override
    public String get(int index) {
        checkIndexOutOfBounds(index);
        return data[index];
    }

    @Override
    public boolean contains(String value) {
        for (String element : data) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
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
    public boolean remove(String value) {
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        removeByIndex(index);
        return true;
    }

    @Override
    public String remove(int index) {
        checkIndexOutOfBounds(index);
        String value = data[index];
        removeByIndex(index);
        return value;
    }

    @Override
    public void clear() {
        capacity = INITIAL_CAPACITY;
        size = INITIAL_SIZE;
        data = new String[capacity];
    }

    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    private void expandCapacity() {
        capacity *= 2;
        String[] newData = new String[capacity];
        copyArray(newData, data);
        data = newData;
    }

    private void copyArray(String[] newData, String[] oldData) {
        for (int i = 0; i < oldData.length; i++) {
            newData[i] = oldData[i];
        }
    }

    private void checkIndexOutOfBounds(int index) {
        if (index >= size) {
            throw new RuntimeException();
        }
    }

    private void removeByIndex(int index) {
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
    }
}
