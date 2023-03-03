package techcourse;

public class SimpleLinkedList implements SimpleList {

    private Node head;
    private Node tail;
    private int size;

    @Override
    public boolean add(final String value) {
        if (isEmpty()) {
            return addFirst(value);
        }
        return addLast(value);
    }

    @Override
    public void add(int index, final String value) {
        validateAddIndex(index);
        if (isEmpty() || index == 0) {
            addFirst(value);
            return;
        }
        if (size == index) {
            addLast(value);
            return;
        }
        Node node = node(index);
        addBetween(node.prev, node, value);
    }

    private boolean addFirst(final String value) {
        size++;
        if (size == 1) {
            head = Node.first(value, head);
            tail = head;
            return true;
        }
        head.prev = Node.first(value, head);
        head = head.prev;
        return true;
    }

    private boolean addLast(final String value) {
        size++;
        Node last = Node.last(tail, value);
        tail.next = last;
        tail = last;
        return true;
    }

    private void addBetween(final Node prev, final Node next, final String element) {
        size++;
        Node node = new Node(prev, element, next);
        prev.next = node;
        next.prev = node;
    }

    private Node node(int index) {
        Node temp = head;
        while (index > 0) {
            temp = temp.next;
            index--;
        }
        return temp;
    }

    private void validateAddIndex(final int index) {
        if (size < index || index < 0) {
            throw new IndexOutOfBoundsException(String.format("index %d out of length %d", index, size));
        }
    }

    @Override
    public String set(int index, final String value) {
        validateIndex(index);

        Node node = node(index);
        String replaced = node.element;
        node.element = value;
        return replaced;
    }

    private void validateIndex(final int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(String.format("index %d out of length %d", index, size));
        }
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return node(index).element;
    }

    @Override
    public boolean contains(final String value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(final String value) {
        Node temp = head;
        int index = 0;
        while (temp != null) {
            if (temp.element.equals(value)) {
                return index;
            }
            temp = temp.next;
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
        return head == null;
    }

    @Override
    public boolean remove(final String value) {
        if (isEmpty()) {
            return false;
        }
        if (head.element.equals(value)) {
            removeFirst();
            return true;
        }
        try {
            remove(indexOf(value));
            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public String remove(final int index) {
        validateIndex(index);
        if (index == 0) {
            return removeFirst();
        }
        if (size == index + 1) {
            return removeLast();
        }
        return removeBetween(index);
    }

    private String removeFirst() {
        size--;
        if (size == 0) {
            String removed = head.element;
            head = null;
            tail = null;
            return removed;
        }
        String removed = head.element;
        head = head.next;
        return removed;
    }

    private String removeLast() {
        size--;
        String removed = tail.element;
        tail = tail.prev;
        tail.next = null;
        return removed;
    }

    private String removeBetween(final int index) {
        size--;
        Node node = node(index);
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        return node.element;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    private static class Node {
        String element;
        Node next;
        Node prev;

        Node(Node prev, String element, Node next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        static Node first(final String element, final Node next) {
            return new Node(null, element, next);
        }

        static Node last(final Node prev, final String element) {
            return new Node(prev, element, null);
        }
    }
}