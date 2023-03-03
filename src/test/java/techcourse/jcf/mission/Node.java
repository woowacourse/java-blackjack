package techcourse.jcf.mission;

import java.util.Objects;

public class Node<T> {
    private final T value;
    private Node<T> nextNode;
    private Node<T> prevNode;

    public Node(T value) {
        this(value, null, null);
    }

    public Node(T value, Node<T> prevNode, Node<T> nextNode) {
        this.value = value;
        this.prevNode = prevNode;
        this.nextNode = nextNode;
        if (prevNode != null) {
            prevNode.setNextNode(this);
        }
        if (nextNode != null) {
            nextNode.setPrevNode(this);
        }
    }

    public boolean hasNextNode() {
        return nextNode != null;
    }

    public void setNextNode(Node<T> node) {
        this.nextNode = node;
    }

    public void setPrevNode(Node<T> node) {
        this.prevNode = node;
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public Node<T> getPrevNode() {
        return prevNode;
    }


    public T getValue() {
        return value;
    }

    public boolean isValueMatch(T value) {
        return Objects.equals(this.value, value);
    }
}
