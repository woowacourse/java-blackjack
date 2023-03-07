package minimission.list;

public class Node {
    public Object value;
    public Node next;

    public Node(Object value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
