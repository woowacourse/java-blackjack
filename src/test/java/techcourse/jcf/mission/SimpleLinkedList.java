package techcourse.jcf.mission;

public class SimpleLinkedList implements SimpleList {

    private Node head;
    private int size;

    private class Node {
        String data;
        Node next;

        public Node(final String data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public boolean add(final String value) {
        Node node = new Node(value);

        if (head == null) {
            head = node;
            return true;
        }

        Node headNode = head;
        while (headNode.next != null) {
            headNode = headNode.next;
        }

        headNode.next = node;
        size++;
        return true;
    }

    @Override
    public void add(final int index, final String value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index가 올바른지 확인하세요.");
        }

        Node node = new Node(value);

        if (index == 0) {
            node.next = head;
            head = node;
            return;
        }

        Node headNode = head;
        for (int i = 0; i < index - 1; i++) {
            headNode = headNode.next;
        }

        node.next = headNode.next;
        headNode.next = node;
        size++;
    }

    @Override
    public String set(final int index, final String value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index가 올바른지 확인하세요.");
        }

        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        String oldValue = currentNode.data;
        currentNode.data = value;

        return oldValue;
    }

    @Override
    public String get(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index에 해당하는 값이 없습니다.");
        }

        Node headNode = head;
        for (int i = 0; i < index; i++) {
            headNode = headNode.next;
        }

        return headNode.data;
    }

    @Override
    public boolean contains(final String value) {
        Node headNode = head;

        while (headNode != null) {
            if (headNode.data.equals(value)) {
                return true;
            }
            headNode = headNode.next;
        }

        return false;
    }

    @Override
    public int indexOf(final String value) {
        int index = 0;
        Node headNode = head;

        while (headNode != null) {
            if (headNode.data.equals(value)) {
                return index;
            }
            headNode = headNode.next;
            index++;
        }

        throw new IndexOutOfBoundsException("해당하는 값이 없습니다.");
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
        if (head == null) {
            return false;
        }

        if (head.data.equals(value)) {
            head = head.next;
            size--;
            return true;
        }

        Node headNode = head;
        while (headNode.next != null) {
            if (headNode.next.data.equals(value)) {
                headNode.next = headNode.next.next;
                size--;
                return true;
            }
            headNode = headNode.next;
        }

        return false;
    }

    @Override
    public String remove(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index가 올바른지 확인하세요.");
        }

        String valueOfDeleting;
        if (index == 0) {
            valueOfDeleting = head.data;
            head = head.next;
            return valueOfDeleting;
        }

        Node headNode = head;
        for (int i = 0; i < index - 1; i++) {
            headNode = headNode.next;
        }

        headNode.next = headNode.next.next;
        size--;

        return headNode.next.data;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }
}
