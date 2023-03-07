package study;

public class Node<T> {
    public T value;
    public Node<T> before;
    public Node<T> next;

    public Node(final T value) {
        this.value = value;
        before = null;
        next = null;
    }

}
