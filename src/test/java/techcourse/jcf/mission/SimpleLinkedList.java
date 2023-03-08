package techcourse.jcf.mission;

public class SimpleLinkedList<T> implements SimpleList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SimpleLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    @Override
    public boolean add(T value) {
        return addNode(value);
    }

    private boolean addNode(T value) {
        size++;
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = node;
            return true;
        }
        tail.setNext(node);
        tail = node;
        return true;
    }

    @Override
    public void add(int index, T value) {
        size++;
        Node<T> node = new Node<>(value);
        if (index == 0) {
            node.setNext(head);
            head = node;
            return;
        }
        Node<T> lastNode = getLastNodeByIndex(index);
        node.setNext(lastNode.getNext());
        lastNode.setNext(node);
    }

    @Override
    public T set(int index, T value) {
        Node<T> lastNode = getLastNodeByIndex(index);
        if (lastNode == null) {
            head.setValue(value);
            return head.getValue();
        }
        lastNode.getNext().setValue(value);
        return value;
    }

    @Override
    public T get(int index) {
        Node<T> lastNode = getLastNodeByIndex(index);
        if (lastNode == null) {
            return head.getValue();
        }
        return lastNode.getNext().getValue();
    }

    private Node getLastNodeByIndex(int index) {
        validateIndex(index);
        Node lastNode = null;
        Node node = head;
        for (int i = 0; i < index - 1; i++) {
            lastNode = node;
            node = node.getNext();
        }
        return lastNode;
    }

    private void validateIndex(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean contains(T value) {
        Node node = head;
        boolean isFound = false;
        while (node != null && !isFound) {
            isFound = node.getValue() == value;
            node = node.getNext();
        }
        return isFound;
    }

    @Override
    public int indexOf(T value) {
        Node node = head;
        boolean isFound = false;
        int index = 0;
        while (node != null && !isFound) {
            isFound = node.getValue() == value;
            node = node.getNext();
            index++;
        }
        if (isFound) {
            return index - 1;
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
        if (!contains(value)) {
            return false;
        }
        removeNode(value);
        size--;
        return true;
    }

    private boolean removeNode(T value) {
        Node lastNode = null;
        Node node = head;
        boolean isFound = node.getValue() == value;
        while (node.getNext() != null && !isFound) {
            lastNode = node;
            node = node.getNext();
            isFound = node.getValue() == value;
        }
        return removeValue(lastNode, node, isFound);
    }

    private boolean removeValue(Node lastNode, Node node, boolean isFound) {
        if (!isFound) {
            return false;
        }
        if (lastNode == null) {
            head = head.getNext();
            return true;
        }
        if (node == null) {
            lastNode.setNext(null);
            return true;
        }
        lastNode.setNext(node.getNext());
        return true;
    }

    @Override
    public T remove(int index) {
        Node<T> lastNode = getLastNodeByIndex(index);
        if (lastNode == null) {
            T value = head.getValue();
            head = head.getNext();
            return value;
        }
        Node<T> node = lastNode.getNext();
        lastNode.setNext(node.getNext());
        size--;
        return node.getValue();
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}
