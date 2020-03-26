package blackjack.domain.card.component;

public enum Type {
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    KING(10, "K"),
    QUEEN(10, "Q"),
    JACK(10, "J"),
    ACE(11, "A");

    private final int value;
    private final String type;

    Type(int value, String type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
