package domain.participant;

public class Money {

    private final int value;

    public Money(String rawValue) {
        this.value = Integer.parseInt(rawValue);
    }
}
