package mission;

public class SimpleLinkedList<TYPE> implements SimpleList<TYPE> {

    transient int size = 0;
    transient Node<TYPE> first;
    transient Node<TYPE> last;

    private static class Node<TYPE> {
        TYPE item;
        Node<TYPE> next;
        Node<TYPE> prev;

        Node(Node<TYPE> prev, TYPE element, Node<TYPE> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public SimpleLinkedList() {
    }

    @Override
    public boolean add(TYPE value) {
        linkLast(value);
        return true;
    }

    @Override
    public void add(int index, TYPE value) {
        checkPositionIndex(index);

        if (index == size)
            linkLast(value);
        else
            linkBefore(value, node(index));
    }

    private void checkPositionIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("잘못된 Index 입니다.");
        }
    }

    @Override
    public TYPE set(int index, TYPE value) {
        checkPositionIndex(index);
        Node<TYPE> x = node(index);
        TYPE oldValue = x.item;
        x.item = value;

        return oldValue;
    }

    @Override
    public TYPE get(int index) {
        checkPositionIndex(index);
        return node(index).item;
    }

    @Override
    public boolean contains(TYPE value) {
        return indexOf(value) > -1;
    }

    @Override
    public int indexOf(TYPE value) {
        int index = 0;
        if (value == null) {
            for (Node<TYPE> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<TYPE> x = first; x != null; x = x.next) {
                if (value.equals(x.item))
                    return index;
                index++;
            }
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
    public boolean remove(TYPE value) {
        final int index = indexOf(value);
        if (index != -1) {
            unlink(node(index));
            return true;
        }
        return false;
    }

    @Override
    public TYPE remove(int index) {
        checkPositionIndex(index);
        return unlink(node(index));
    }

    @Override
    public void clear() {
        for (Node<TYPE> x = first; x != null; ) {
            Node<TYPE> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }

    Node<TYPE> node(int index) {
        if (index < (size >> 1)) {
            Node<TYPE> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<TYPE> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    private void linkLast(TYPE e) {
        final Node<TYPE> l = last;
        final Node<TYPE> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    void linkBefore(TYPE e, Node<TYPE> succ) {
        // assert succ != null;
        final Node<TYPE> pred = succ.prev;
        final Node<TYPE> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
    }

    TYPE unlink(Node<TYPE> x) {
        final TYPE element = x.item;
        final Node<TYPE> next = x.next;
        final Node<TYPE> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

}
