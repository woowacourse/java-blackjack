package model;

public enum CardNumber {
    ACE_ELEVEN(11),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE_ONE(1);

    private final int number;

    CardNumber(final int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
