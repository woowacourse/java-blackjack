package generic;

public class SimpleLinkedList<E> implements SimpleList<E> {

    private Node head;
    private Node tail;
    private int size;

    SimpleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    private class Node {

        Node(E value, Node next) {
            this.value = value;
            this.next = next;
        }

        private Node next;
        private E value;
    }


    @Override
    public boolean add(final E value) {
        Node created = new Node(value, tail);
        if (head == null) {
            head = created;
        }

        if (tail != null) {
            tail.next = created;
        }
        tail = created;
        size++;
        return true;
    }

    @Override
    public void add(final int index, final E value) {
        Node temp = searchNode(index);
        temp.next = new Node(value, temp.next);
        size++;
    }

    private Node searchNode(final int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    @Override
    public E set(final int index, final E value) {
        Node temp = searchNode(index);
        temp.value = value;
        return temp.value;
    }

    @Override
    public E get(final int index) {
        Node node = searchNode(index);
        return node.value;
    }

    @Override
    public boolean contains(final E value) {
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
    public int indexOf(final E value) {
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
    public boolean remove(final E value) {
        Node before = head;
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (node.value.equals(value)) {
                before.next = node.next;
                node = null;
                size--;
                return true;
            }
            before = node;
            node = node.next;
        }
        return false;
    }

    @Override
    public E remove(final int index) {
        Node before = head;
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                E removed = node.value;
                before.next = node.next;
                node = null;
                size--;
                return removed;
            }
            before = node;
            node = node.next;
        }
        return null;
    }

    @Override
    public void clear() {
        Node node = head;
        while (node != null) {
            node = head.next;
            head = null;
            head = node;
        }
        size = 0;
    }

    @Override
    public String toString() {
        return "SimpleLinkedList{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }
}
