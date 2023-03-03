package list;

public class CustomLinkedList implements SimpleList {
    private Node head;
    private int size;

    public CustomLinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public boolean add(String value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, String value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node newNode = new Node(value);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            newNode.next = curr.next;
            curr.next = newNode;
        }
        size++;
    }

    @Override
    public String set(int index, String value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        String temp = curr.value;
        curr.value = value;
        return temp;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

    @Override
    public boolean contains(String value) {
        Node curr = head;
        while (curr != null) {
            if (curr.value.equals(value)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    @Override
    public int indexOf(String value) {
        Node curr = head;
        int index = 0;
        while (curr != null) {
            if (curr.value.equals(value)) {
                return index;
            }
            curr = curr.next;
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
    public boolean remove(String value) {
        Node curr = head;
        Node prev = null;
        while (curr != null) {
            if (curr.value.equals(value)) {
                if (prev == null) {
                    head = curr.next;
                } else {
                    prev.next = curr.next;
                }
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        String removedValue;
        if (index == 0) {
            removedValue = head.value;
            head = head.next;
        } else {
            Node curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            removedValue = curr.next.value;
            curr.next = curr.next.next;
        }
        size--;
        return removedValue;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }
}
