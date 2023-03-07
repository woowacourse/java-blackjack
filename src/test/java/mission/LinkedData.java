package mission;

public class LinkedData<T> {
    private T value;
    private LinkedData<T> next;

    public LinkedData(T value) {
        this.value = value;
    }

    public void setNext(LinkedData<T> next) {
        this.next = next;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T value() {
        return this.value;
    }

    public LinkedData<T> next() {
        return next;
    }
}
