package domain;

public class Money {
    private final int value;

    public Money(String value) {
        this.value = Integer.parseInt(value);
    }
}
