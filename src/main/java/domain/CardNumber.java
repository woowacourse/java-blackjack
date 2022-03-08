package domain;

public enum CardNumber {

    ACE("1", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    KING("K", 10),
    QUEEN("Q", 10),
    JACK("J", 10),
    ;

    private final String name;
    private final int number;

    CardNumber(final String name, final int number) {
        this.name = name;
        this.number = number;
    }
}
