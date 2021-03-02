package blackjack;

public enum CardNumber {
    ACE("A", 11, 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10);

    private final String symbol;
    private final int number;
    private final int extraNumber;

    CardNumber(final String symbol, final int number, final int extraNumber) {
        this.symbol = symbol;
        this.number = number;
        this.extraNumber = extraNumber;
    }

    CardNumber(final String symbol, final int number) {
        this(symbol, number, number);
    }

    public int getNumber() {
        return this.number;
    }
}
