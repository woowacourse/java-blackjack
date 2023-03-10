package techcourse.jcf.mission;

import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleList<E> {

    private Node<E> firstNode;

    private int size;

    public SimpleLinkedList() {
        this.firstNode = null;
        this.size = 0;
    }

    @Override
    public boolean add(E value) {
        Node<E> iterator = firstNode;
        if (Objects.isNull(iterator)) {
            addFirstNode(new Node<E>(value));
            return true;
        }
        for (; !iterator.isNextNull(); iterator = iterator.getNext())
            ;
        ++size;
        iterator.setNext(new Node<E>(value));
        return true;
    }

    @Override
    public void add(int index, E value) {
        validateOutOfBoundForAdd(index);
        if (size == 0 && index == 0) {
            addFirstNode(new Node<E>(value));
            return;
        }
        Node<E> beforeNode = firstNode;
        Node<E> iterator = firstNode;
        nodeMovingDestination(beforeNode, iterator, index);
        nodeChaining(beforeNode, new Node<E>(value), iterator);
        ++size;
    }

    private void nodeMovingDestination(Node<E> before, Node<E> iterator, Integer destination) {
        for (int i = 0; i < destination; i++) {
            before = iterator;
            iterator = iterator.getNext();
        }
    }

    private Node<E> findLastNode(Node<E> first) {
        Node<E> iterator = first;
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

    private void nodeChaining(Node<E> first, Node<E> second, Node<E> third) {
        first.setNext(second);
        second.setNext(third);
    }

    private void addFirstNode(Node<E> node) {
        if (!(size == 0 && Objects.isNull(firstNode))) {
            throw new IllegalStateException("초기 노드 추가 함수 호출이 잘못되었습니다.");
        }
        ++size;
        firstNode = node;
    }

    @Override
    public E set(int index, E value) {
        if (!(0 <= index && index < this.size)) {
            throw new IllegalArgumentException("인덱스는 범위를 벗어날 수 없습니다.");
        }
        Node<E> iterator = firstNode;
        for (int i = 0; i < index; i++) {
            iterator = iterator.getNext();
        }
        E oldValue = iterator.getValue();
        iterator.setValue(value);
        return oldValue;
    }

    @Override
    public E get(int index) {
        if (!(0 <= index && index < this.size)) {
            throw new IllegalArgumentException("인덱스는 범위를 벗어날 수 없습니다.");
        }
        Node<E> iterator = firstNode;
        for (int i = 0; i < index; i++) {
            iterator = iterator.getNext();
        }
        return iterator.getValue();
    }

    @Override
    public boolean contains(E value) {
        Node<E> iterator = firstNode;
        for (int i = 0; i < size && !(iterator.getValue().equals(value)); i++) {
            iterator = iterator.getNext();
        }
        if (Objects.isNull(iterator)) {
            return false;
        }
        return true;
    }

    @Override
    public int indexOf(E value) {
        int index = 0;
        Node<E> iterator = firstNode;
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
        return size == 0;
    }

    @Override
    public boolean remove(E value) {
        Node<E> before = firstNode;
        Node<E> iterator = firstNode;
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
    public E remove(int index) {
        validateOutOfBoundForSearch(index);
        Node<E> before = firstNode;
        Node<E> iterator = firstNode;
        for (int i = 0; i < index; i++) {
            before = iterator;
            iterator = iterator.getNext();
        }
        before.setNext(iterator.getNext());
        --size;
        return (E) iterator.getValue();
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
