package domain.card;

public enum Rank {
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
    KING("K", 10),
    ACE("A", 11);

    private final String value;
    private final int score;

    Rank(final String value, final int score) {
        this.value = value;
        this.score = score;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public String getValue() {
        return value;
    }

    public int getScore() {
        return score;
    }
}
