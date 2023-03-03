package mission;

public class SimpleArrayList<T> implements SimpleList<T> {

    private Object[] simpleArrayList;
    private int size;

    public <T> SimpleArrayList() {
        simpleArrayList = new Object[2];
        size = 0;
    }

    public SimpleArrayList(T ... values) {
        this();
        for (T value : values) {
            add(value);
        }
    }

    @Override
    public boolean add(T value) {
        if (size == simpleArrayList.length) {
            makeArraySizeDouble();
        }
        simpleArrayList[size] = value;
        size += 1;
        return true;
    }

    @Override
    public void add(int index, T value) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size == simpleArrayList.length) {
            makeArraySizeDouble();
        }
        for (int count = 0; count < size; count++) {
            if (count > index) {
                simpleArrayList[count] = simpleArrayList[count - 1];
            }
            simpleArrayList[index] = value;
        }
        size += 1;
    }

    @Override
    public T set(int index, T value) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        T targetValue = (T) simpleArrayList[index];
        simpleArrayList[index] = value;
        return targetValue;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (T) simpleArrayList[index];
    }

    @Override
    public boolean contains(T value) {
        for (int count = 0; count < size; count++) {
            if (value.equals((T)simpleArrayList[count])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int count = 0; count < size; count++) {
            if (value.equals(simpleArrayList[count])) {
                return count;
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
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        T indexValue = (T) simpleArrayList[index];
        for (int count = index + 1; count < size; count++) {
            simpleArrayList[count - 1] = simpleArrayList[count];
        }
        size -= 1;
        return indexValue;
    }

    @Override
    public boolean remove(T value) {
        int index = -1;
        for (int count = 0; count < size; count++) {
            if (value.equals((T) simpleArrayList[count])) {
                index = count;
            }
        }
        if (index > -1) {
            for (int count = index + 1; count < size; count++) {
                simpleArrayList[count - 1] = simpleArrayList[count];
            }
            size -= 1;
            return true;
        }
        return false;
    } //배열의 크기가 작아지면 다시 동적으로 크기를 조절할 것인지?

    @Override
    public void clear() {
        simpleArrayList = new Object[2];
        size = 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i < size; i++) {
            String comma = i == size - 1? "" : ", ";
            stringBuilder.append(simpleArrayList[i]).append(comma);
        }
        return stringBuilder.append("}").toString();
    }

    private void makeArraySizeDouble() {
        Object[] simpleArrayList = new Object[size * 2];
        if (size >= 0) {
            System.arraycopy(this.simpleArrayList, 0, simpleArrayList, 0, size);
        }
        this.simpleArrayList = simpleArrayList;
    }
}
