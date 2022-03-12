package blackjack.trumpcard;

public enum TrumpNumber {
    ACE(1, "ACE"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "JACK"),
    QUEEN(10, "QUEEN"),
    KING(10, "KING"),
    ;

    private final int value;
    private final String valueOfString;

    TrumpNumber(int value, String valueOfString) {
        this.value = value;
        this.valueOfString = valueOfString;
    }

    public int sumTo(int otherValue) {
        return this.value + otherValue;
    }

    public String getValueOfString() {
        return this.valueOfString;
    }

    public int getValue() {
        return this.value;
    }
}
