package blackjack.domain;

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

    private final String letter;
    private final int score;

    Rank(final String letter, final int score) {
        this.letter = letter;
        this.score = score;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public String getLetter() {
        return letter;
    }

    public int getScore() {
        return score;
    }
}
