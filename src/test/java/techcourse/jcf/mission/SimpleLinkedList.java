package techcourse.jcf.mission;

public class SimpleLinkedList implements SimpleList {

    private int size;
    private Node head;
    private Node tail;

    private static class Node {
        String value;
        Node next;

        Node(String value, Node next) {
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
    public boolean add(final String value) {
        Node lastNode = tail;
        Node newNode = new Node(value, null);

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
    public void add(final int index, final String value) {
        checkValidIndex(index);
        Node newNode = new Node(value, null);

        if (index == 0) {
            Node firstNode = head;
            head = newNode;
            newNode.next = firstNode;
        } else if (index == size) {
            tail.next = newNode;
            tail = newNode;
        } else {
            Node temp = node(index - 1);
            Node nextNode = temp.next;
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
    public String set(final int index, final String value) {
        checkValidIndex(index);
        Node temp = node(index);
        String oldValue = temp.value;
        temp.value = value;

        return oldValue;
    }

    @Override
    public String get(final int index) {
        checkValidIndex(index);
        Node temp = node(index);
        return temp.value;
    }

    @Override
    public boolean contains(final String value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(final String value) {
        int index = 0;
        for (Node temp = head; temp != null; temp = temp.next) {
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
    public boolean remove(final String value) {
        int index = indexOf(value);

        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public String remove(final int index) {
        checkValidIndex(index);

        String oldValue = null;
        if (index == 0) {
            oldValue = head.value;
            head = head.next;
        } else if (index == size - 1) {
            oldValue = tail.value;
            Node temp = node(index - 1);
            tail = temp;
            temp.next = null;
        } else {
            Node temp = node(index - 1);
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

    private Node node(final int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }
}
