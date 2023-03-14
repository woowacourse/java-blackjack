package mission;

public class SimpleLinkedList<T> implements SimpleList<T> {

    private LinkedData<T> head;
    private LinkedData<T> tail;

    private int size;

    public SimpleLinkedList() {
        size = 0;
    }

    @Override
    public boolean add(T value) {
        if (head == null) {
            head = new LinkedData<>(value);
            tail = head;
            size++;
            return true;
        }
        tail.setNext(new LinkedData<>(value));
        tail = tail.next();
        size++;
        return true;
    }

    @Override
    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        LinkedData<T> target = head;
        for (int i = 0; i < index - 1; i++) {
            target = target.next();
        }
        LinkedData<T> inputData = new LinkedData<>(value);
        inputData.setNext(target.next());
        target.setNext(inputData);
        size++;
    }

    @Override
    public T set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        LinkedData<T> target = head;
        for (int i = 0; i < index; i++) {
            target = target.next();
        }
        T oldValue = target.value();
        target.setValue(value);
        return oldValue;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        LinkedData<T> target = head;
        for (int i = 0; i < index; i++) {
            target = target.next();
        }
        return target.value();
    }

    @Override
    public boolean contains(T value) {
        LinkedData<T> target = head;
        while (target.next() != null) {
            if (target.value().equals(value)) {
                return true;
            }
            target = target.next();
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        LinkedData<T> target = head;
        int repeatCount = 0;
        while (target.next() != null) {
            if (target.value().equals(value)) {
                return repeatCount;
            }
            target = target.next();
            repeatCount++;
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
        LinkedData<T> target = head;
        while (target.next() != null) {
            if (target.next().value().equals(value)) {
                target.setNext(target.next().next());
                size--;
                return true;
            }
            target = target.next();
        }
        return false;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        LinkedData<T> target = head;
        for (int i = 0; i < index - 1; i++) {
            target = target.next();
        }
        T oldValue = target.next().value();
        target.setNext(target.next().next());
        size--;
        return oldValue;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}
