package minimission.list;

public class SimpleLinkedList<E> implements SimpleList<E> {
    private Node head;
    private int size;

    public SimpleLinkedList() {
        head = null;
        size = 0;
    }

    @Override
    public boolean add(E value) {
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
    public void add(int index, E value) {
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
    @SuppressWarnings("unchecked")
    public E set(int index, E value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        E temp = (E) curr.value;
        curr.value = value;
        return temp;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return (E) curr.value;
    }

    @Override
    public boolean contains(E value) {
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
    public int indexOf(E value) {
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
    public boolean remove(E value) {
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
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Object removedValue;
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
        return (E) removedValue;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }
    
    @Override
    public String toString() {
        return "CustomLinkedList{" +
                "head=" + head +
                ", size=" + size +
                '}';
    }
}
