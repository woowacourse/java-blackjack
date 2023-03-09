//package minimission;
//
//public class SimpleLinkedList implements SimpleList {
//
//    private Node head;
//    private Node tail;
//    private int size;
//
//    public SimpleLinkedList() {
//        this.size = 0;
//    }
//
//    @Override
//    public boolean add(String value) {
//        Node node = new Node(value, null);
//
//        if (size == 0) {
//            head = node;
//            tail = node;
//        }
//
//        tail.next = node;
//        tail = node;
//
//        size++;
//
//        return true;
//    }
//
//    @Override
//    public void add(int index, String value) {
//        validateIndexOutOfRange(index);
//
//        Node node = new Node(value, null);
//
//        Node prevNode = head;
//        for (int i = 0; i < index - 1; i++) {
//            prevNode = head.next;
//        }
//        Node indexNode = prevNode.next;
//
//        prevNode.next = node;
//        node.next = indexNode;
//        size++;
//    }
//
//    @Override
//    public String set(int index, String value) {
//        validateIndexOutOfRange(index);
//
//        Node indexNode = head;
//        for (int i = 0; i < index; i++) {
//            indexNode = indexNode.next;
//        }
//
//        String originalValue = indexNode.value;
//        indexNode.value = value;
//
//        return originalValue;
//    }
//
//    @Override
//    public String get(int index) {
//        validateIndexOutOfRange(index);
//
//        Node indexNode = head;
//        for (int i = 0; i < index; i++) {
//            indexNode = indexNode.next;
//        }
//
//        return indexNode.value;
//    }
//
//    @Override
//    public boolean contains(String value) {
//        Node node = head;
//
//        while (node.next != null) {
//            if (node.value.equals(value)) {
//                return true;
//            }
//            node = node.next;
//        }
//
//        return false;
//    }
//
//    @Override
//    public int indexOf(String value) {
//        Node node = head;
//        int index = 0;
//
//        while (node.next != null) {
//            if (node.value.equals(value)) {
//                return index;
//            }
//            node = node.next;
//            index++;
//        }
//
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
//    public boolean remove(String value) {
//        if (!contains(value)) {
//            return false;
//        }
//
//        Node node = head;
//        while (node.next != tail) {
//            Node nextNode = node.next;
//
//            if (nextNode.value.equals(value)) {
//                node.next = nextNode.next;
//                size--;
//                return true;
//            }
//            node = node.next;
//        }
//
//        node.next = null;
//        tail = node;
//        size--;
//
//        return true;
//    }
//
//    @Override
//    public String remove(int index) {
//        validateIndexOutOfRange(index);
//
//        Node prevNode = head;
//        for (int i = 0; i < index - 1; i++) {
//            prevNode = prevNode.next;
//        }
//
//        if (index == size - 1) {
//            prevNode.next = null;
//            tail = prevNode;
//            size--;
//            return prevNode.value;
//        }
//
//        Node removedNode = prevNode.next;
//        prevNode.next = removedNode.next;
//        size--;
//        return removedNode.value;
//    }
//
//    @Override
//    public void clear() {
//        head = null;
//        size = 0;
//    }
//
//    private void validateIndexOutOfRange(int index) {
//        if (index < 0 || index >= size) {
//            throw new IndexOutOfBoundsException();
//        }
//    }
//
//    class Node {
//        String value;
//        Node next;
//
//        public Node(String value, Node next) {
//            this.value = value;
//            this.next = next;
//        }
//    }
//}
