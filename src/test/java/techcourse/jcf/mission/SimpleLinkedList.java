package techcourse.jcf.mission;

public class SimpleLinkedList implements SimpleList {

    private int size = 0;

    private Node<String> first;

    private Node<String> last;

    // 빈 리스트로 생성
    public SimpleLinkedList() {}

    private static class Node<String> {
        String item;
        Node<String> next;
        Node<String> prev;

        Node(Node<String> prev, String element, Node<String> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public boolean add(String value) {
        linkLast(value);
        return true;
    }

    private void linkLast(String value) {
        final Node<String> l = last;
        final Node<String> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, String value) {
        checkPositionIndex(index);

        if (index == size)
            linkLast(value);
        else
            linkBefore(value, node(index));
    }

    private void checkPositionIndex(int index) {
        if (!(index >= 0 && index <= size))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void linkBefore(String value, Node<String> succ) {
        final Node<String> pred = succ.prev;
        final Node<String> newNode = new Node<>(pred, value, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
    }

    // 해당 인덱스의 노드 탐색하여 반환
    private Node<String> node(int index) {
        if (index < (size >> 1)) {
            Node<String> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<String> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
    @Override
    public String set(int index, String value) {
        checkElementIndex(index);
        Node<String> x = node(index);
        String oldVal = x.item;
        x.item = value;
        return oldVal;
    }

    // 인덱스가 음수이거나, size보다 크다면 예외 처리
    private void checkElementIndex(int index) {
        if (!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }


    @Override
    public String get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    @Override
    public boolean contains(String value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(String value) {
        int index = 0;
        // LinkedList의 첫 번째 노드부터 next 노드로 계속 이동하면서 값 체크
        if (value == null) {
            for (Node<String> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<String> x = first; x != null; x = x.next) {
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
    public boolean remove(String value) {
        if (value == null) {
            for (Node<String> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<String> x = first; x != null; x = x.next) {
                if (value.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    // 연결된 링크를 끊는 기능
    private String unlink(Node<String> x) {
        final String element = x.item;
        final Node<String> next = x.next;
        final Node<String> prev = x.prev;

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

    @Override
    public String remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    @Override
    public void clear() {
        for (Node<String> x = first; x != null; ) {
            Node<String> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }
}
