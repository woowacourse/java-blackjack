package domain.card;

public enum CardNumber {
    A(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    KING(10),
    QUEEN(10),
    JACK(10);

    public final int value;

    CardNumber(int value) {
        this.value = value;
    }
}
