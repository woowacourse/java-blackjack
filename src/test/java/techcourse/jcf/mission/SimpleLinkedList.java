package techcourse.jcf.mission;

public class SimpleLinkedList<T> implements SimpleList<T> {
    private static class Node<T> {
        private T value;
        private Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private final Node<T> head;
    private int size;

    public SimpleLinkedList() {
        this.head = new Node<>(null, null);
        this.size = 0;
    }

    @Override
    public boolean add(T value) {
        Node<T> lastNode = findLastNode();
        lastNode.next = new Node<>(value, null);
        this.size++;
        return true;
    }

    private Node<T> findLastNode() {
        Node<T> currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    @Override
    public void add(int index, T value) {
        Node<T> targetNode = findTargetNode(index);
        targetNode.next = new Node<>(value, targetNode.next);
        this.size++;
    }

    private Node<T> findTargetNode(int index) {
        Node<T> currentNode = head;
        for (int i = 0; i <= index; i++) {
            if (currentNode == null) {
                throw new IndexOutOfBoundsException();
            }
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    @Override
    public T set(int index, T value) {
        Node<T> targetNode = findTargetNode(index);
        T oldValue = targetNode.value;
        targetNode.value = value;
        return oldValue;
    }

    @Override
    public T get(int index) {
        return findTargetNode(index).value;
    }

    @Override
    public boolean contains(T value) {
        Node<T> currentNode = head.next;
        while (currentNode != null) {
            if (currentNode.value.equals(value)) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        int index = 0;
        Node<T> currentNode = head.next;
        while (currentNode != null) {
            if (currentNode.value.equals(value)) {
                return index;
            }
            currentNode = currentNode.next;
            index++;
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
        Node<T> currentNode = head.next;
        while (currentNode != null) {
            if (currentNode.value.equals(value)) {
                currentNode.next = currentNode.next.next;
                this.size--;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public T remove(int index) {
        Node<T> previousNode = head;
        if (index > 0) {
            previousNode = findTargetNode(index - 1);
        }
        T oldValue = previousNode.next.value;
        previousNode.next = previousNode.next.next;
        this.size--;
        return oldValue;
    }

    @Override
    public void clear() {
        Node<T> currentNode = head.next;
        while (currentNode != null) {
            Node<T> nextNode = currentNode.next;
            currentNode.value = null;
            currentNode.next = null;
            currentNode = nextNode;
        }
        size = 0;
    }
}
