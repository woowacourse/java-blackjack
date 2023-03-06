package techcourse.jcf.mission;

public class SimpleLinkedList<T> implements SimpleList<T> {
    private Node<T> head;
    private Node<T> tail;

    public SimpleLinkedList() {
        this(null);
    }

    public SimpleLinkedList(T[] values) {
        this.head = new Node<>();
        this.tail = this.head;
        for (T value: values) {
            this.tail.next = new Node<>(value);
            this.tail = this.tail.next;
        }
    }


    @Override
    public T get(int index) {
        Node<T> pointer = getNodeOf(index);
        return pointer.value;
    }

    @Override
    public boolean add(T value) {
        this.tail.next = new Node<>(value);
        this.tail = this.tail.next;
        return true;
    }

    @Override
    public void add(int index, T value) {
        Node<T> prevPointer = this.head;
        Node<T> nextPointer = this.head;
        for (int i = 0; i <= index; i++) {
            prevPointer = nextPointer;
            if (prevPointer == null) {
                throw new IndexOutOfBoundsException();
            }
            nextPointer = nextPointer.next;
        }
        prevPointer.next = new Node<>(value, nextPointer);
    }

    @Override
    public T set(int index, T value) {
        Node<T> pointer = getNodeOf(index);
        T valueBeforeSet = pointer.value;
        pointer.value = value;
        return valueBeforeSet;
    }

    @Override
    public boolean contains(T value) {
        Node<T> pointer = this.head.next;
        while (pointer != null) {
            if (pointer.value.equals(value)) {
                return true;
            }
            pointer = pointer.next;
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        Node<T> pointer = this.head.next;
        int index = 0;
        while (pointer != null) {
            if (pointer.value.equals(value)) {
                return index;
            }
            pointer = pointer.next;
            index += 1;
        }
        return -1;
    }

    @Override
    public int size() {
        Node<T> pointer = this.head.next;
        int size = 0;
        while (pointer != null) {
            pointer = pointer.next;
            size += 1;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.head.next == null;
    }

    @Override
    public boolean remove(T value) {
        Node<T> previousPointer = this.head;
        Node<T> nextPointer = this.head.next;
        while (nextPointer != null) {
            if (nextPointer.value.equals(value)) {
                previousPointer.next = nextPointer.next;
                return true;
            }
            previousPointer = nextPointer;
            nextPointer = nextPointer.next;
        }
        return false;
    }

    @Override
    public T remove(int index) {
        Node<T> previousPointer = getNodeOf(index - 1);
        Node<T> pointerToRemove = getNodeOf(index);
        T valueToRemove = pointerToRemove.value;
        previousPointer.next = pointerToRemove.next;
        return valueToRemove;
    }

    @Override
    public void clear() {
        this.head = new Node<T>();
        this.tail = this.head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> pointer = this.head.next;
        while (pointer != null) {
            sb.append(pointer.value);
            sb.append(", ");
            pointer = pointer.next;
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        return sb.toString();
    }

    private Node<T> getNodeOf(int index) {
        Node<T> pointer = this.head;
        for (int i = 0; i <= index; i++) {
            pointer = pointer.next;
            if (pointer == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return pointer;
    }
    class Node<T> {

        private T value;
        private SimpleLinkedList<T>.Node<T> next;

        public Node() {
            this(null);
        }

        public Node(T value) {
            this(value, null);
        }

        public Node(T value, SimpleLinkedList<T>.Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
