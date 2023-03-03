package mission;

public class Node {
    private Node nextNode;
    private String value;

    public Node(String value) {
        this.value = value;
    }

    public void setNextNode(Node node) {
        if (nextNode != null) {
            nextNode.setNextNode(nextNode);
            return;
        }

        nextNode = node;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
