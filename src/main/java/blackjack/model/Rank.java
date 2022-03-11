package blackjack.model;

public enum Rank {
    ACE("A", 11, 1), TWO("2", 2, 2),
    THREE("3", 3, 3), FOUR("4", 4, 4),
    FIVE("5", 5, 5), SIX("6", 6, 6),
    SEVEN("7", 7, 7), EIGHT("8", 8, 8),
    NINE("9", 9, 9), TEN("10", 10, 10),
    JACK("J", 10, 10), QUEEN("Q", 10, 10),
    KING("K", 10, 10);

    private final String symbol;
    private final int softRank;
    private final int hardRank;

    Rank(String symbol, int softRank, int hardRank) {
        this.symbol = symbol;
        this.softRank = softRank;
        this.hardRank = hardRank;
    }

    public int soft() {
        return softRank;
    }

    public int hard() {
        return hardRank;
    }

    public String symbol() {
        return symbol;
    }
}
