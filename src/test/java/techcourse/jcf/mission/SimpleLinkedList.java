package techcourse.jcf.mission;

public class SimpleLinkedList<E> implements SimpleList<E> {

    private int size;
    private Node<E> head;
    private Node<E> tail;

    private static class Node<E> {
        E value;
        Node<E> next;

        Node(final E value, final Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    public SimpleLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public boolean add(final E value) {
        Node<E> lastNode = tail;
        Node<E> newNode = new Node<>(value, null);

        if (head == null && tail == null) {
            head = newNode;
        } else {
            lastNode.next = newNode;
        }
        tail = newNode;
        size++;
        return true;
    }

    @Override
    public void add(final int index, final E value) {
        checkValidIndex(index);
        Node<E> newNode = new Node<>(value, null);

        if (index == 0) {
            Node<E> firstNode = head;
            head = newNode;
            newNode.next = firstNode;
        }
        else if (index == size) {
            tail.next = newNode;
            tail = newNode;
        }
        else {
            Node<E> temp = node(index - 1);
            Node<E> nextNode = temp.next;
            temp.next = newNode;
            newNode.next = nextNode;
        }
        size++;
    }

    private void checkValidIndex(final int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + "Size: " + size);
        }
    }

    @Override
    public E set(final int index, final E value) {
        checkValidIndex(index);
        Node<E> temp = node(index);
        E oldValue = temp.value;
        temp.value = value;

        return oldValue;
    }

    @Override
    public E get(final int index) {
        checkValidIndex(index);
        Node<E> temp = node(index);
        return temp.value;
    }

    @Override
    public boolean contains(final E value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(final E value) {
        int index = 0;
        for (Node<E> temp = head; temp != null; temp = temp.next) {
            if (temp.value.equals(value)) {
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
    public boolean remove(final E value) {
        int index = indexOf(value);

        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public E remove(final int index) {
        checkValidIndex(index);

        E oldValue = null;
        if (index == 0) {
            oldValue = head.value;
            head = head.next;
        }
        else if (index == size - 1) {
            oldValue = tail.value;
            Node<E> temp = node(index - 1);
            tail = temp;
            temp.next = null;
        }
        else {
            Node<E> temp = node(index - 1);
            temp.next = temp.next.next;
        }
        size--;
        return oldValue;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    private Node<E> node(final int index) {
        Node<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }
}
