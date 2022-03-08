package blackjack.domain;

public enum Denomination {
    ACE(1, "ACE"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    KING(10, "KING"),
    QUEEN(10, "QUEEN"),
    JACK(10, "JACK");

    private final int value;
    private final String name;

    Denomination(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }
}
