package model;

public enum CardNumber {
    ONE("A", 1),
    ELEVEN("A", 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    JACK("J", 10);

    private final String number;
    private final int value;

    CardNumber(String number, int value) {
        this.number = number;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
