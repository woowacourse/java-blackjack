package domain;

public enum Rank {
    ACE("A", 11),
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

    final String symbol;
    final int value;

    Rank(final String symbol, final int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String symbol(){
        return symbol;
    }

    public int value(){
        return value;
    }

    public boolean isAce(){
        return this == ACE;
    }
}
