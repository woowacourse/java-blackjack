package blackjack;

public enum Rank {
    ACE(11, 1), TWO(2, 2), THREE(3, 3),
    FOUR(4, 4), FIVE(5, 5), SIX(6, 6),
    SEVEN(7, 7), EIGHT(8, 8), NINE(9, 9), TEN(10, 10),
    JACK(10, 10), QUEEN(10, 10), KING(10, 10);

    private final int softRank;
    private final int hardRank;

    Rank(int softRank, int hardRank) {
        this.softRank = softRank;
        this.hardRank = hardRank;
    }

    public int soft() {
        return softRank;
    }

    public int hard() {
        return hardRank;
    }
}
