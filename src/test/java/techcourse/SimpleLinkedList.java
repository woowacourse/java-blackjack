package techcourse;

public class SimpleLinkedList<T> implements SimpleList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public boolean add(final T value) {
        if (isEmpty()) {
            return addFirst(value);
        }
        return addLast(value);
    }

    @Override
    public void add(int index, final T value) {
        validateAddIndex(index);
        if (isEmpty() || index == 0) {
            addFirst(value);
            return;
        }
        if (size == index) {
            addLast(value);
            return;
        }
        Node<T> node = node(index);
        addBetween(node.prev, node, value);
    }

    private boolean addFirst(final T value) {
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

    private boolean addLast(final T value) {
        size++;
        Node<T> last = Node.last(tail, value);
        tail.next = last;
        tail = last;
        return true;
    }

    private void addBetween(final Node<T> prev, final Node<T> next, final T element) {
        size++;
        Node<T> node = new Node<>(prev, element, next);
        prev.next = node;
        next.prev = node;
    }

    private Node<T> node(int index) {
        Node<T> temp = head;
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
    public T set(int index, final T value) {
        validateIndex(index);

        Node<T> node = node(index);
        T replaced = node.element;
        node.element = value;
        return replaced;
    }

    private void validateIndex(final int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(String.format("index %d out of length %d", index, size));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return node(index).element;
    }

    @Override
    public boolean contains(final T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(final T value) {
        Node<T> temp = head;
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
    public boolean remove(final T value) {
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
    public T remove(final int index) {
        validateIndex(index);
        if (index == 0) {
            return removeFirst();
        }
        if (size == index + 1) {
            return removeLast();
        }
        return removeBetween(index);
    }

    private T removeFirst() {
        size--;
        if (size == 0) {
            T removed = head.element;
            head = null;
            tail = null;
            return removed;
        }
        T removed = head.element;
        head = head.next;
        return removed;
    }

    private T removeLast() {
        size--;
        T removed = tail.element;
        tail = tail.prev;
        tail.next = null;
        return removed;
    }

    private T removeBetween(final int index) {
        size--;
        Node<T> node = node(index);
        Node<T> prev = node.prev;
        Node<T> next = node.next;
        prev.next = next;
        next.prev = prev;
        return node.element;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        static <T> Node<T> first(final T element, final Node<T> next) {
            return new Node<>(null, element, next);
        }

        static <T> Node<T> last(final Node<T> prev, final T element) {
            return new Node<T>(prev, element, null);
        }
    }
}
