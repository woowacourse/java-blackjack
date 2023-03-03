package techcourse.jcf.mission;

import java.util.Objects;

public class Node {
    private String value;
    private Node next;

    public Node(String value) {
        this.value = value;
        this.next = null;
    }

    public void appendNode(Node node) {
        this.next = node;
    }

    public boolean isNextNull() {
        if (Objects.isNull(next)) {
            return true;
        }
        return false;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node node) {
        next = node;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
