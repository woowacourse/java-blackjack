package list;

public class SimpleLinkedList<T> implements SimpleList<T> {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private T value;
        private Node previous;
        private Node next;

        public Node(T value) {
            this.value = value;
            this.previous = null;
            this.next = null;
        }
    }

    @Override
    public boolean add(T value) {
        Node node = new Node(value);
        if (size == 0) {
            head = node;
            tail = node;
            size++;
            return true;
        }
        node.previous = tail;
        tail.next = node;
        tail = node;
        size++;
        return true;
    }

    @Override
    public void add(int index, T value) {
        checkIndexOutOfBounds(index);
        if (size == index) {
            add(value);
            return;
        }
        if (index == 0) {
            Node node = new Node(value);
            head.previous = node;
            node.next = head;
            head = node;
            size++;
            return;
        }
        Node node = head;
        for (int i = 1; i < index; i++) {
            node = head.next;
        }
        Node newNode = new Node(value);
        Node previous = node.previous;
        previous.next = newNode;
        node.previous = newNode;
        newNode.previous = previous;
        newNode.next = node;
        size++;
    }

    @Override
    public T set(int index, T value) {
        checkIndexOutOfBounds(index);
        Node node = getNodeByIndex(index);
        node.value = value;
        return value;
    }

    @Override
    public T get(int index) {
        checkIndexOutOfBounds(index);
        Node node = getNodeByIndex(index);
        return node.value;
    }

    @Override
    public boolean contains(T value) {
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(value)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(value)) {
                return i;
            }
            node = node.next;
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
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(value)) {
                remove(i);
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public T remove(int index) {
        checkIndexOutOfBounds(index);
        if (index == 0) {
            Node node = head;
            head = node.next;
            head.previous = null;
            size--;
            return node.value;
        }
        if (index == size) {
            throw new RuntimeException();
        }
        if (index == size - 1) {
            Node node = tail;
            tail = node.previous;
            tail.next = null;
            size--;
            return node.value;
        }
        Node node = getNodeByIndex(index);
        Node previous = node.previous;
        Node next = node.next;
        previous.next = next;
        next.previous = previous;
        size--;
        return node.value;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public void printAll() {
        Node node = head;
        for (int i = 0; i < size; i++) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    private void checkIndexOutOfBounds(int index) {
        if (index >= size) {
            throw new RuntimeException();
        }
    }

    private Node getNodeByIndex(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = head.next;
        }
        return node;
    }
}
