package techcourse.jcf.mission;

public class SimpleLinkedList<T> implements SimpleList<T> {

    private Node<T> head;
    private int size;

    private class Node<T> {
        T data;
        Node<T> next;

        public Node(final T data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public boolean add(final T value) {
        Node<T> node = new Node<>(value);

        if (head == null) {
            head = node;
            return true;
        }

        Node<T> headNode = head;
        while (headNode.next != null) {
            headNode = headNode.next;
        }

        headNode.next = node;
        size++;
        return true;
    }

    @Override
    public void add(final int index, final T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        Node<T> node = new Node<>(value);

        if (index == 0) {
            node.next = head;
            head = node;
            return;
        }

        Node<T> headNode = head;
        for (int i = 0; i < index - 1; i++) {
            headNode = headNode.next;
        }

        node.next = headNode.next;
        headNode.next = node;
        size++;
    }

    @Override
    public T set(final int index, final T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        T oldValue = currentNode.data;
        currentNode.data = value;

        return oldValue;
    }

    @Override
    public T get(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        Node<T> headNode = head;
        for (int i = 0; i < index; i++) {
            headNode = headNode.next;
        }

        return headNode.data;
    }

    @Override
    public boolean contains(final T value) {
        Node<T> headNode = head;

        while (headNode != null) {
            if (headNode.data.equals(value)) {
                return true;
            }
            headNode = headNode.next;
        }

        return false;
    }

    @Override
    public int indexOf(final T value) {
        int index = 0;
        Node<T> headNode = head;

        while (headNode != null) {
            if (headNode.data.equals(value)) {
                return index;
            }
            headNode = headNode.next;
            index++;
        }

        throw new IndexOutOfBoundsException("Value not found.");
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
    public boolean remove(final T value) {
        if (head == null) {
            return false;
        }

        if (head.data.equals(value)) {
            head = head.next;
            size--;
            return true;
        }

        Node<T> headNode = head;
        while (headNode.next != null) {
            if (headNode.next.data.equals(value)) {
                headNode.next = headNode.next.next;
                size--;
                return true;
            }
            headNode = headNode.next;
        }

        return false;
    }

    @Override
    public T remove(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index가 올바른지 확인하세요.");
        }

        T valueOfDeleting;
        if (index == 0) {
            valueOfDeleting = head.data;
            head = head.next;
            return valueOfDeleting;
        }

        Node<T> headNode = head;
        for (int i = 0; i < index - 1; i++) {
            headNode = headNode.next;
        }

        headNode.next = headNode.next.next;
        size--;

        return headNode.next.data;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }
}
