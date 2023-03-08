package domain.deck;

public enum Rank {

    ACE("A", 1),
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

    private final String rank;
    private final int score;

    Rank(final String rank, final int score) {
        this.rank = rank;
        this.score = score;
    }

    public String getRank() {
        return rank;
    }

    public int getScore() {
        return score;
    }
}
