package domain.card;

public enum Rank {
    A(11, "A"),
    ONE(1, "1"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private final int value;
    private final String name;

    Rank(final int value, final String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public boolean isAce() {
        return value == A.getValue();
    }

    public String getName() {
        return name;
    }
}
