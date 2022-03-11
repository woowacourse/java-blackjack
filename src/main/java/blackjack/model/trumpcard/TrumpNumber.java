package blackjack.model.trumpcard;

public enum TrumpNumber {
    ACE(1, "A"),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K"),
    ;

    private final int value;
    private final String name;


    TrumpNumber(int value, String name) {
        this.value = value;
        this.name = name;
    }

    TrumpNumber(int value) {
        this(value, null);
    }

    public int sumTo(int otherValue) {
        return this.value + otherValue;
    }

    public int getValue() {
        return this.value;
    }


    @Override
    public String toString() {
        if (this.name == null) {
            return String.valueOf(this.value);
        }
        return this.name;
    }
}
