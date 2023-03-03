package techcourse.jcf.mission;

public class SimpleArrayList<T> implements SimpleList<T> {
    private final static int DEFAULT_SIZE = 16;

    private Object[] arrayNodes;
    private int index = 0;

    public SimpleArrayList() {
        this.arrayNodes = new Object[DEFAULT_SIZE];
    }

    @Override
    public boolean add(T value) {
        if (index >= arrayNodes.length - 1) {
            increaseArraySize();
        }
        arrayNodes[index++] = value;
        return true;
    }

    private void increaseArraySize() {
        Object[] newArrayNodes = new Object[arrayNodes.length * 2];
        System.arraycopy(arrayNodes, 0, newArrayNodes, 0, index);
        this.arrayNodes = newArrayNodes;
    }

    @Override
    public void add(int index, T value) {
        if (index >= arrayNodes.length - 1) {
            increaseArraySize();
        }
        for (int i = index; i <= this.index; i++) {
            arrayNodes[i] = arrayNodes[i + 1];
        }
        arrayNodes[index] = value;
        this.index++;
    }

    @Override
    public T set(int index, T value) {
        arrayNodes[index] = value;
        return value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) arrayNodes[index];
    }

    @Override
    public boolean contains(T value) {
        for (int i = 0; i < index; i++) {
            if (arrayNodes[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < index; i++) {
            if (arrayNodes[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    @Override
    public boolean remove(T value) {
        int targetIndex = indexOf(value);
        if (targetIndex == -1) {
            return false;
        }
        removeTarget(targetIndex);
        return true;
    }

    private void removeTarget(int targetIndex) {
        for (int i = targetIndex; i <= index; i++) {
            arrayNodes[i] = arrayNodes[i + 1];
        }
        index--;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        T target = (T) arrayNodes[index];
        removeTarget(index);
        return target;
    }

    @Override
    public void clear() {
        this.arrayNodes = new Object[DEFAULT_SIZE];
        this.index = 0;
    }
}
