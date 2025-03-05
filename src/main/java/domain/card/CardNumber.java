package domain.card;

public enum CardNumber {
    ACE("ACE", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("JACK", 10),
    KING("KING", 10),
    QUEEN("QUEEN", 10);

    private final String name;
    private final int value;

    CardNumber(final String name, final int value) {
        this.name = name;
        this.value = value;
    }


}
