package study;

public class SimpleLinkedList<T> implements SimpleList<T> {
    public Node<T> start = null;

    public SimpleLinkedList(T... args) {
        for (T arg : args) {
            add(arg);
        }
    }

    @Override
    public boolean add(final T value) {
        Node<T> newNode = new Node(value);
        if (start == null) {
            start = newNode;
            return true;
        }

        Node<T> head = start;
        while (head.next != null) {
            head = head.next;
        }
        head.next = newNode;
        newNode.before = head;
        return true;
    }

    @Override
    public void add(final int index, final T value) {
        Node<T> newNode = new Node(value);
        Node<T> head = start;
        for (int i = 0; i < index - 1; i++) {
            head = head.next;
        }
        Node<T> tmp = head.next;
        head.next = newNode;
        tmp.before = newNode;
        newNode.next = tmp;
        newNode.before = head;
    }

    @Override
    public T set(final int index, final T value) {
        Node<T> head = start;
        for (int i = 0; i < index - 1; i++) {
            head = head.next;
        }
        T old = head.value;
        head.value = value;
        return old;
    }

    @Override
    public T get(final int index) {
        Node<T> head = start;
        for (int i = 0; i < index; i++) {
            head = head.next;
        }
        return head.value;
    }

    @Override
    public boolean contains(final T value) {
        if (start == null) {
            return false;
        }
        Node<T> head = start;
        while (true) {
            if (head.value.equals(value)) {
                return true;
            }
            if (head.next == null) {
                return false;
            }
            head = head.next;
        }
    }

    @Override
    public int indexOf(final T value) {
        int index = 0;
        if (start == null) {
            return -1;
        }
        Node<T> head = start;
        while (true) {
            if (head.value.equals(value)) {
                return index;
            }
            if (head.next == null) {
                return -1;
            }
            head = head.next;
            index++;
        }
    }

    @Override
    public int size() {
        int index = 1;
        if (start == null) {
            return 0;
        }
        Node<T> head = start;
        while (true) {
            if (head.next == null) {
                return index;
            }
            head = head.next;
            index++;
        }
    }

    @Override
    public boolean isEmpty() {
        return start == null;
    }

    @Override
    public boolean remove(final T value) {
        if (start == null || !contains(value)) {
            return false;
        }
        Node<T> head = start;
        Node<T> prev = start;
        while (true) {
            if (head.next == null) {
                return false;
            }
            if (head.value.equals(value)) {
                prev.next = head.next;
                return true;
            }
            prev = head;
            head = head.next;
        }
    }

    @Override
    public T remove(final int index) {
        if (size() <= index) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> head = start;
        Node<T> prev = start;
        for (int i = 0; i < index; i++) {
            prev = head;
            head = head.next;
        }
        prev.next = head.next;
        head.next.before = prev;
        return head.value;
    }

    @Override
    public void clear() {
        start.next = null;
        start = null;
    }
}
