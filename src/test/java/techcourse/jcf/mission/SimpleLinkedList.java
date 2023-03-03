package techcourse.jcf.mission;

import java.util.Objects;

public class SimpleLinkedList implements SimpleList {

    private Node firstNode;

    private int size;

    public SimpleLinkedList() {
        this.firstNode = null;
        this.size = 0;
    }

    @Override
    public boolean add(String value) {
        Node iterator = firstNode;
        if (Objects.isNull(iterator)) {
            addFirstNode(new Node(value));
            return true;
        }
        for (; !iterator.isNextNull(); iterator = iterator.getNext())
            ;
        ++size;
        iterator.setNext(new Node(value));
        return true;
    }

    @Override
    public void add(int index, String value) {
        validateOutOfBoundForAdd(index);
        if (size == 0 && index == 0) {
            addFirstNode(new Node(value));
            return;
        }
        Node beforeNode = firstNode;
        Node iterator = firstNode;
        nodeMovingDestination(beforeNode, iterator, index);
        nodeChaining(beforeNode, new Node(value), iterator);
        ++size;
    }

    private void nodeMovingDestination(Node before, Node iterator, Integer destination) {
        for (int i = 0; i < destination; i++) {
            before = iterator;
            iterator = iterator.getNext();
        }
    }

    private Node findLastNode(Node first) {
        Node iterator = first;
        while (!Objects.isNull(iterator.getNext())){
            iterator = iterator.getNext();
        }
        return iterator;
    }

    private void validateOutOfBoundForAdd(int index) {
        if (!(0 <= index && index <= this.size)) {
            throw new IllegalArgumentException("인덱스는 범위를 벗어날 수 없습니다.");
        }
    }

    private void nodeChaining(Node first, Node second, Node third) {
        first.setNext(second);
        second.setNext(third);
    }

    private void addFirstNode(Node node) {
        if (!(size == 0 && Objects.isNull(firstNode))) {
            throw new IllegalStateException("초기 노드 추가 함수 호출이 잘못되었습니다.");
        }
        ++size;
        firstNode = node;
    }

    @Override
    public String set(int index, String value) {
        if (!(0 <= index && index < this.size)) {
            throw new IllegalArgumentException("인덱스는 범위를 벗어날 수 없습니다.");
        }
        Node iterator = firstNode;
        for (int i = 0; i < index; i++) {
            iterator = iterator.getNext();
        }
        String oldValue = iterator.getValue();
        iterator.setValue(value);
        return oldValue;
    }

    @Override
    public String get(int index) {
        if (!(0 <= index && index < this.size)) {
            throw new IllegalArgumentException("인덱스는 범위를 벗어날 수 없습니다.");
        }
        Node iterator = firstNode;
        for (int i = 0; i < index; i++) {
            iterator = iterator.getNext();
        }
        return iterator.getValue();
    }

    @Override
    public boolean contains(String value) {
        Node iterator = firstNode;
        for (int i = 0; i < size && !(iterator.getValue().equals(value)); i++) {
            iterator = iterator.getNext();
        }
        if (Objects.isNull(iterator)) {
            return false;
        }
        return true;
    }

    @Override
    public int indexOf(String value) {
        int index = 0;
        Node iterator = firstNode;
        for (; index < size && !(iterator.getValue().equals(value)); index++) {
            iterator = iterator.getNext();
        }
        if (Objects.isNull(iterator)) {
            return -1;
        }
        return index;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(String value) {
        Node before = firstNode;
        Node iterator = firstNode;
        for (int i = 0; i < size && !(iterator.getValue().equals(value)); i++) {
            before = iterator;
            iterator = iterator.getNext();
        }
        if (Objects.isNull(iterator)) {
            return false;
        }
        before.setNext(iterator.getNext());
        --size;
        return true;
    }

    @Override
    public String remove(int index) {
        validateOutOfBoundForSearch(index);
        Node before = firstNode;
        Node iterator = firstNode;
        for (int i = 0; i < index; i++) {
            before = iterator;
            iterator = iterator.getNext();
        }
        before.setNext(iterator.getNext());
        --size;
        return iterator.getValue();
    }

    private void validateOutOfBoundForSearch(int index){
        if (!(0 <= index && index < this.size)) {
            throw new IllegalArgumentException("인덱스는 범위를 벗어날 수 없습니다.");
        }
    }

    @Override
    public void clear() {
        firstNode = null;
        size = 0;
    }
}
