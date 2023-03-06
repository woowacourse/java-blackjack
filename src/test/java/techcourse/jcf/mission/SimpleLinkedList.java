package techcourse.jcf.mission;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SimpleLinkedList <T> implements SimpleList <T> {

    private Node<T> pointer;
    private Node<T> root;
    private int size;

    public SimpleLinkedList() {
        root = new Node();
        pointer = root;
        size = 0;
    }

    public SimpleLinkedList(T... values) {
        this();

        for (T value : values) {
            add(value);
        }
    }

    @Override
    public boolean add(T value) {
        Node<T> insertionNode = new Node(value);
        pointer.next = insertionNode;
        insertionNode.previous = pointer;
        pointer = insertionNode;
        size++;
        return true;
    }

    @Override
    public void add(int index, T value) {
        validateIndex(index);
        int nodeIndex = 0;
        Node<T> currentNode = root.next;

        while (nodeIndex != index) {
            currentNode = currentNode.next;
            nodeIndex++;
        }

        Node<T> previousNode = currentNode.previous;
        Node<T> insertionNode = new Node(value);
        currentNode.previous = insertionNode;
        previousNode.next = insertionNode;
        insertionNode.next = currentNode;
        insertionNode.previous = previousNode;
        size++;
    }

    @Override
    public T set(int index, T value) {
        validateIndex(index);
        int nodeIndex = 0;
        Node<T> currentNode = root.next;

        while (nodeIndex != index) {
            currentNode = currentNode.next;
            nodeIndex++;
        }

        T indexValue = currentNode.value;
        currentNode.value = value;
        return indexValue;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        int nodeIndex = 0;
        Node<T> currentNode = root.next;

        while (nodeIndex != index) {
            currentNode = currentNode.next;
            nodeIndex++;
        }

        return currentNode.value;
    }

    @Override
    public boolean contains(T value) {
        Node<T> currentNode = root.next;
        while (currentNode != null) {
            if (currentNode.value.equals(value)) {
                return true;
            }

            currentNode = currentNode.next;
        }

        return false;
    }

    @Override
    public int indexOf(T value) {
        int index = 0;
        Node<T> currentNode = root.next;
        while (currentNode != null) {
            if (currentNode.value.equals(value)) {
                return index;
            }

            currentNode = currentNode.next;
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
    public boolean remove(T value) {
        Node<T> currentNode = root.next;

        while (currentNode != null) {
            if (currentNode.value.equals(value)) {
                removeNode(currentNode);
                return true;
            }

            currentNode = currentNode.next;
        }

        return false;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        int nodeIndex = 0;
        Node<T> currentNode = root.next;

        while (nodeIndex != index) {
            currentNode = currentNode.next;
            nodeIndex++;
        }

        removeNode(currentNode);
        return currentNode.value;
    }

    @Override
    public void clear() {
        root.next = null;
        pointer = root;
        size = 0;
    }

    private static class Node <T> {
        T value;
        Node<T> previous;
        Node<T> next;

        Node() {
            previous = null;
            next = null;
        }

        Node(T value) {
            this();
            this.value = value;
        }
    }

    private void removeNode(Node<T> currentNode) {
        if (currentNode == pointer) {
            pointer = pointer.previous;
        }

        Node<T> nextNode = currentNode.next;
        Node<T> previous = currentNode.previous;

        if (nextNode != null) {
            nextNode.previous = previous;
        }
        previous.next = nextNode;
        size--;
    }

    private void validateIndex(int index) {
        if (!(0 <= index && index < size)) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString() {
        return String.format("[%s]", Arrays.stream(makeResult())
                .map(element -> element + "")
                .collect(Collectors.joining(", ")));
    }

    private T[] makeResult() {
        Object[] result = new Object[size];
        int index = 0;
        Node<T> currentNode = root.next;

        while (currentNode != null) {
            result[index++] = currentNode.value;
            currentNode = currentNode.next;
        }

        return (T[]) result;
    }

}
