package techcourse.jcf.mission;

import java.util.NoSuchElementException;

//public class SimpleLinkedList implements SimpleList {
//
//    private Node head;
//    private Node tail;
//    private int size;
//
//    public SimpleLinkedList() {
//        this.head = null;
//        this.tail = null;
//        this.size = 0;
//    }
//
//    public void addFirst(final String value) {
//        Node newNode = new Node(value);
//        newNode.next = this.head;
//        this.head = newNode;
//        size++;
//    }
//
//    @Override
//    public boolean add(final String value) {
//        addLast(value);
//        return true;
//    }
//
//    @Override
//    public void add(final int index, final String value) {
//        isInRange(index);
//
//        if (index == 0) {
//            addFirst(value);
//            return;
//        }
//
//        if (index == size) {
//            addLast(value);
//            return;
//        }
//
//        final Node prevNode = search(index - 1);
//        final Node nextNode = prevNode.next;
//        final Node newNode = new Node(value);
//
//        prevNode.next = newNode;
//        newNode.next = nextNode;
//
//        size++;
//    }
//
//    public void addLast(final String value) {
//        final Node newNode = new Node(value);
//
//        if (size == 0) {
//            addFirst(value);
//            return;
//        }
//        this.tail.next = newNode;
//        this.tail = newNode;
//        size++;
//    }
//
//    @Override
//    public String set(final int index, final String value) {
//        final Node replaceNode = search(index);
//        final String oldValue = replaceNode.data;
//        replaceNode.data = value;
//        return oldValue;
//    }
//
//    @Override
//    public String get(final int index) {
//        return search(index).data;
//    }
//
//    @Override
//    public boolean contains(final String value) {
//        return indexOf(value) >= 0;
//    }
//
//    @Override
//    public int indexOf(final String value) {
//        int index = 0;
//
//        for (Node i = head; i != null; i = i.next) {
//            if (value.equals(i.data)) {
//                return index;
//            }
//            index++;
//        }
//        return -1;
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    @Override
//    public boolean remove(final String value) {
//        Node prevNode = this.head;
//        Node i = head;
//
//        for (; i != null; i = i.next) {
//            if (value.equals(i.data)) {
//                break;
//            }
//            prevNode = i;
//        }
//
//        if (i == null) {
//            return false;
//        }
//
//        if (i.equals(head)) {
//            remove();
//            return true;
//        }
//
//        prevNode.next = i.next;
//
//        if (prevNode.next == null) {
//            tail = prevNode;
//        }
//        i.data = null;
//        i.next = null;
//        size--;
//
//        return true;
//    }
//
//    public String remove() {
//        Node headNode = this.head;
//
//        if (headNode == null) {
//            throw new NoSuchElementException();
//        }
//
//        String element = headNode.data;
//
//        final Node nextNode = head.next;
//
//        head.data = null;
//        head.next = null;
//
//        head = nextNode;
//        size--;
//
//        if (size == 0) {
//            tail = null;
//        }
//        return element;
//    }
//
//    @Override
//    public String remove(final int index) {
//
//        if (index == 0) {
//            return remove();
//        }
//
//        isInRange(index);
//
//        final Node prevNode = search(index - 1);
//        final Node removedNode = prevNode.next;
//        final Node nextNode = removedNode.next;
//
//        String element = removedNode.data;
//
//        prevNode.next = nextNode;
//
//        if (prevNode.next == null) {
//            tail = prevNode;
//        }
//
//        removedNode.next = null;
//        removedNode.data = null;
//        size--;
//
//        return element;
//    }
//
//    @Override
//    public void clear() {
//        for (Node x = head; x != null; ) {
//            Node nextNode = x.next;
//            x.data = null;
//            x.next = null;
//            x = nextNode;
//        }
//        head = tail = null;
//        size = 0;
//    }
//
//
//    private Node search(int index) {
//        isInRange(index);
//        Node node = this.head;
//
//        for (int i = 0; i < index; i++) {
//            node = node.next;
//        }
//        return node;
//    }
//
//    private void isInRange(final int index) {
//        if (size < index || index < 0) {
//            throw new IndexOutOfBoundsException();
//        }
//    }
//}
