package mission;

public class SimpleArrayList<T> implements SimpleList<T> {
    private final int fixedSize = 10;
    private Object[] arrayList;
    private int point = 0;

    public <T> SimpleArrayList() {
        this.arrayList = new Object[fixedSize];
    }

    public SimpleArrayList(T... values) {
        this();
        for (T value : values) {
            add(value);
        }
    }

    @Override
    public boolean add(T value) {
        beforeAdd(value);
        arrayList[point] = value;
        point += 1;
        return true;
    }

    private void beforeAdd(T value) {
        checkNull(value);

        if (point == arrayList.length) {
            Object[] copy = new String[arrayList.length + fixedSize];
            for (int i = 0; i < point; i++) {
                copy[i] = arrayList[point];
            }
            this.arrayList = copy;
        }
    }

    private void checkNull(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public void add(int index, T value) {
        checkBound(index);
        beforeAdd(value);

        Object[] copy = new String[arrayList.length];
        for (int i = 0; i < arrayList.length; i++) {
            if (i < index) {
                copy[i] = arrayList[i];
            }
            if (i == index) {
                copy[index] = value;
            }
            if (i > index) {
                copy[i] = arrayList[i - 1];
            }
        }
        this.arrayList = copy;
        point += 1;
    }

    private void checkBound(int index) {
        if (index > point - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T set(int index, T value) {
        checkBound(index);
        T originalValue = (T)arrayList[index];

        Object[] copy = new String[arrayList.length];
        for (int i = 0; i < arrayList.length; i++) {
            if (i < index) {
                copy[i] = arrayList[i];
            }
            if (i == index) {
                copy[index] = value;
            }
            if (i > index) {
                copy[i] = arrayList[i];
            }
        }
        this.arrayList = copy;
        return originalValue;
    }

    @Override
    public T get(int index) {
        checkBound(index);
        return (T)arrayList[index];
    }

    @Override
    public boolean contains(T value) {
        checkNull(value);
        for (Object arrayValue : arrayList) {
            if (value.equals((T)arrayValue)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < point; i++) {
            if (value.equals(arrayList[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return point;
    }

    @Override
    public boolean isEmpty() {
        return point == 0;
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
        checkBound(index);
        T originalValue = (T)arrayList[index];

        Object[] copy = new String[arrayList.length];
        for (int i = 0; i < point; i++) {
            if (i < index) {
                copy[i] = arrayList[i];
            }
            if (i >= index) {
                copy[i] = arrayList[i + 1];
            }
        }
        this.arrayList = copy;
        point -= 1;
        return originalValue;
    }

    @Override
    public void clear() {
        this.arrayList = new Object[fixedSize];
        this.point = 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i < point; i++) {
            String coma = i == point - 1 ? "" : ", ";
            stringBuilder.append(arrayList[i]).append(coma);
        }
        return stringBuilder.append("}").toString();
    }
}
