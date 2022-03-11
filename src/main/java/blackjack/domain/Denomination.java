package blackjack.domain;

public enum Denomination {

    ACE("A", 11),
    ONE("A", 1),
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
    BURST("burst", 0),
    ;

    private final String rawScore;
    private final int score;

    Denomination(final String rawScore, final int score) {
        this.rawScore = rawScore;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getRawScore() {
        return rawScore;
    }
}
