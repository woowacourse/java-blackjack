package blackjack.model;

public enum Rank {
    ACE("A", 1), TWO("2", 2),
    THREE("3", 3), FOUR("4", 4),
    FIVE("5", 5), SIX("6", 6),
    SEVEN("7", 7), EIGHT("8", 8),
    NINE("9", 9), TEN("10", 10),
    JACK("J", 10), QUEEN("Q", 10),
    KING("K", 10);

    private final String symbol;
    private final int rank;

    Rank(String symbol, int value) {
        this.symbol = symbol;
        this.rank = value;
    }

    public int getRank() {
        return rank;
    }

    public String getSymbol() {
        return symbol;
    }
}
