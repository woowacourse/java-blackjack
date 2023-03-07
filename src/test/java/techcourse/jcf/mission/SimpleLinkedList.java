package techcourse.jcf.mission;

public class SimpleLinkedList<T> implements SimpleList<T> {

    private Node<T> node;
    private int size = 0;

    @Override
    public boolean add(T value) {
        if (node == null) {
            this.node = new Node<>(value);
            size++;
            return true;
        }
        Node<T> node = getLastNode();
        new Node<>(value, node, null);
        size++;
        return true;
    }

    private Node<T> getLastNode() {
        Node<T> node = this.node;
        while (node.hasNextNode()) {
            node = node.getNextNode();
        }
        return node;
    }


    @Override
    public void add(int index, T value) {
        Node<T> foundNode = getNode(index);
        Node<T> newNode = new Node<>(value, foundNode.getPrevNode(), foundNode);
        if (index == 0) {
            this.node = newNode;
        }
        size++;
    }

    private Node<T> getNode(int index) {
        validateIndex(index);
        Node<T> node = this.node;
        for (int i = 0; i < index; i++) {
            node = node.getNextNode();
        }
        return node;
    }

    private void validateIndex(int index) {
        if (index + 1 > size || index < 0) {
            throw new IllegalArgumentException("[ERROR] 인덱스 범위 초과");
        }
    }

    @Override
    public T set(int index, T value) {
        Node<T> foundNode = getNode(index);
        Node<T> newNode = new Node<>(value, foundNode.getPrevNode(), foundNode.getNextNode());
        if (index == 0) {
            this.node = newNode;
        }
        return value;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return getNode(index).getValue();
    }

    @Override
    public boolean contains(T value) {
        for (Node<T> node = this.node; node != null; node = node.getNextNode()) {
            if (node.isValueMatch(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        int index = 0;
        for (Node<T> node = this.node; node != null; node = node.getNextNode()) {
            if (node.isValueMatch(value)) {
                return index;
            }
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
        for (Node<T> node = this.node; node != null; node = node.getNextNode()) {
            if (node.isValueMatch(value)) {
                unlink(node);
                return true;
            }
        }
        return false;
    }

    private void unlink(Node<T> node) {
        Node<T> prevNode = node.getPrevNode();
        Node<T> nextNode = node.getNextNode();
        if (prevNode != null) {
            prevNode.setNextNode(nextNode);
        }
        if (nextNode != null) {
            nextNode.setPrevNode(prevNode);
        }
        if (size == 1) {
            this.node = null;
        }
        size--;
    }

    @Override
    public T remove(int index) {
        Node<T> foundNode = getNode(index);
        unlink(foundNode);
        return foundNode.getValue();
    }

    @Override
    public void clear() {
        this.node = null;
        this.size = 0;
    }
}
