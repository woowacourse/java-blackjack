package techcourse.jcf.mission;

import java.util.Objects;

public class Node<E> {
    private E value;
    private Node<E> next;

    public Node(E value) {
        this.value = value;
        this.next = null;
    }

    public void appendNode(Node<E> node) {
        this.next = node;
    }

    public boolean isNextNull() {
        return Objects.isNull(next);
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> node) {
        next = node;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
