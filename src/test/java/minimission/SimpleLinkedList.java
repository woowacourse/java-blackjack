package minimission;

public class SimpleLinkedList implements SimpleList {

    private Node head;

    private int size;

    public SimpleLinkedList() {
    }

    @Override
    public boolean add(final String value) {

        final Node newNode = new Node(null, value);

        if (head == null) {
            head = newNode;
        } else {
            Node curNode = head;

            while (curNode.next != null) {
                curNode = curNode.next;
            }

            curNode.next = newNode;
        }

        size++;
        return true;
    }

    @Override
    public void add(final int index, final String value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("여기에 넣을 수 없습니다.");
        }

        final Node newNode = new Node(null, value);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1 && current != null; i++) {
                current = current.next;
            }
            if (current != null) {
                newNode.next = current.next;
                current.next = newNode;
            }
        }

        size++;
    }

    @Override
    public String set(final int index, final String value) {
        return null;
    }

    @Override
    public String get(final int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("여기에 넣을 수 없습니다.");
        }

        Node node = head;
        int start = index;

        while (start-- > 0) {
            node = node.next;
        }

        return node.value;
    }

    @Override
    public boolean contains(final String value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(final String value) {
        int index = 0;

        for (Node start = head; start != null; start = start.next) {
            if (start.value.equals(value)) {
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
        return head == null;
    }

    @Override
    public boolean remove(final String value) {

        final int index = indexOf(value);
        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    @Override
    public String remove(final int index) {

        final Node targetNode = node(index);
        final Node next = targetNode.next;
        final Node prev = node(index - 1);

        final String removedValue = targetNode.value;

        if (next == null) {
            prev.next = null;
        } else {
            prev.next = next;
            targetNode.next = null;
            targetNode.value = null;
        }

        size--;
        return removedValue;
    }

    void print() {
        for (Node current = head; current != null; current = current.next) {
            System.out.println(current.value + " ");
        }
    }

    Node node(int index) {
        Node node = head;

        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    @Override
    public void clear() {

        for (Node current = head; current != null; ) {
            Node next = current.next;
            current.value = null;
            current.next = null;

            current = next;
        }

        size = 0;
        head = null;
    }

    static class Node {

        Node next;
        String value;

        public Node(final Node next, final String value) {
            this.next = next;
            this.value = value;
        }
    }
}
