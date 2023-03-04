package techcourse.jcf.mission;

public class SimpleArrayList implements SimpleList {

    private static final int STORE_SIZE = 10;

    private String[] data;
    private int size;

    public SimpleArrayList() {
        data = new String[STORE_SIZE];
        size = 0;
    }

    @Override
    public boolean add(final String value) {
        checkRealloc(size + 1);
        data[size++] = value;
        return true;
    }

    @Override
    public void add(final int index, final String value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index를 확인해주세요 : " + index);
        }
        checkRealloc(size + 1);

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = value;
        size++;
    }

    @Override
    public String set(final int index, final String value) {
        validateIndex(index);
        String oldValue = data[index];
        data[index] = value;
        return oldValue;
    }

    @Override
    public String get(final int index) {
        validateIndex(index);
        return data[index];
    }

    @Override
    public boolean contains(final String value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(final String value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(data[i])) {
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
        int index = indexOf(value);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public String remove(final int index) {
        validateIndex(index);
        String oldValue = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null;
        return oldValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    private void checkRealloc(int nextIndex) {
        if (nextIndex > data.length) {
            int newSize = data.length * 2;
            String[] newData = new String[newSize];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
