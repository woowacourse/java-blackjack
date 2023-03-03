package mission;

public class SimpleLinkedList implements SimpleList {
    private Node startNode;

    @Override
    public boolean add(String value) {
        Node node = new Node(value);
        if (startNode == null) {
            startNode = node;
            return true;
        }
        Node nextNode = startNode;
        while (nextNode.getNextNode() != null) {
            nextNode = nextNode.getNextNode();
        }
        nextNode.setNextNode(node);

        return true;
    }

    @Override
    public void add(int index, String value) {
        Node node = new Node(value);

        if (index == 0) {
            node.setNextNode(startNode);
            this.startNode = node;
            return;
        }

        Node targetNode = startNode;
        for (int i = 0; i < index - 1; i++) {
            targetNode = targetNode.getNextNode();
        }

        node.setNextNode(targetNode.getNextNode());
        targetNode.setNextNode(node);
    }

    @Override
    public String set(int index, String value) {
        Node node = startNode;

        for (int i = 0; i < index; i++) {
            node = node.getNextNode();
        }

        String removedValue = node.getValue();
        node.setValue(value);

        return removedValue;
    }

    @Override
    public String get(int index) {
        Node node = startNode;
        for (int i = 0; i < index; i++) {
            node = node.getNextNode();
        }

        return node.getValue();
    }

    @Override
    public boolean contains(String value) {
        Node node = startNode;
        while (node.getNextNode() != null) {
            if (node.getValue().equals(value)) {
                return true;
            }
            node = node.getNextNode();
        }
        return false;
    }

    @Override
    public int indexOf(String value) {
        int index = 0;
        Node node = startNode;

        while (node.getNextNode() != null) {
            if (node.getValue().equals(value)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    @Override
    public int size() {
        int size = 1;
        Node node = startNode;

        while (node.getNextNode() != null) {
            size++;
            node = node.getNextNode();
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        if (startNode == null)
            return true;

        return false;
    }

    @Override
    public boolean remove(String value) {
        Node node = startNode;
        do {
            Node preNode = node;
            if (node.getValue().equals(value)) {
                if (node == startNode) {
                    startNode = node.getNextNode();
                    return true;
                }
                preNode.setNextNode(node.getNextNode());
            }
            node = node.getNextNode();
        } while (node.getNextNode() != null);

        return false;
    }

    @Override
    public String remove(int index) {
        if (index == 0) {
            String value = startNode.getValue();
            startNode = startNode.getNextNode();
            return value;
        }

        Node node = startNode;
        Node preNode = node;

        for (int i = 0; i < index; i++) {
            preNode = node;
            node = node.getNextNode();
            System.out.println("node : " + node + "preNode : " + preNode);
        }

        preNode.setNextNode(node.getNextNode());
        return node.getValue();
    }

    @Override
    public void clear() {
        startNode = null;
    }

    @Override
    public String toString() {
        String str = "[";
        Node node = startNode;
        while (node != null) {
            str += node.getValue();
            if (node.getNextNode() != null) {
                str += ", ";
            }
            node = node.getNextNode();
        }
        str += "]";

        return str;
    }
}
