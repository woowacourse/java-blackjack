package mission;


public class SimpleLinkedList {

    private int size;
    private Node first;
    private Node present;

    private class Node {
        private String value;
        private Node next;

        public Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public String getValue() {
            return this.value;
        }

        public Node getNext() {
            return this.next;
        }
    }

    public SimpleLinkedList() {
        size = 0;
        first = null;
        present = null;
    }

    boolean add(String value) {
        Node node = new Node(value, null);
        size += 1;
        if (first == null) {
            first = node;
            present = node;
            return true;
        }
        present.setNext(node);
        present = node;
        return true;
    }

    void add(int index, String value) throws Exception {
        if (index >= size || index < 0) {
            throw new Exception();
        }

        Node target = first;
        for (int count = 0; count < index - 1; count++) {
            target = target.getNext();
        }

        Node node = new Node(value, target.getNext());
        target.setNext(node);
        size += 1;
    }

    String set(int index, String value) throws Exception {
        if (index >= size || index < 0) {
            throw new Exception();
        }

        Node target = first;
        for (int count = 0; count < index; count++) {
            target = target.getNext();
        }
        String targetValue = target.getValue();
        target.setValue(value);
        return targetValue; //이전에 해당 인덱스에 저장된 값을 반환
    }

    String get(int index) throws Exception {
        if (index >= size || index < 0) {
            throw new Exception();
        }

        Node target = first;
        for (int count = 0; count < index; count++) {
            target = target.getNext();
        }
        return target.getValue();
    }

    boolean contains(String value) {
        Node target = first;
        for (int count = 0; count < size; count++) {
            if (value.equals(target.getValue())) {
                return true;
            }
            target = target.getNext();
        }
        return false;
    }

    int indexOf(String value) throws Exception {
        Node target = first;
        for (int count = 0; count < size; count++) {
            if (value.equals(target.getValue())) {
                return count;
            }
            target = target.getNext();
        }
        throw new Exception();
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    boolean remove(String value) {
        if (value.equals(first.getValue())) {
            first = first.getNext();
            size -= 1;
            return true;
        }

        Node prev = null;
        Node target = first;
        for (int count = 0; count < size; count++) {
            prev = target;
            target = target.getNext();
            if (value.equals(target.getValue())) {
                prev.setNext(target.getNext());
                size -= 1;
                return true;
            }
        }
        return false;
    }

    String remove(int index) throws Exception {
        if (index >= size || index < 0) {
            throw new Exception();
        }

        if (index == 0) {
            String targetValue = first.getValue();
            first = first.getNext();
            size -= 1;
            return targetValue;
        }

        Node prev = null;
        Node target = first;
        for (int count = 0; count < index; count++) {
            prev = target;
            target = target.getNext();
        }
        prev.setNext(target.getNext());
        size -= 1;
        return target.getValue();
    }

    void clear() {
        first = null;
        present = null;
        size = 0;
    }
}
