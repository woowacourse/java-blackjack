package blackjack.model;

public enum Rank {

    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    J(10),
    Q(10),
    K(10);

    private final int defaultScore;

    Rank(int defaultScore) {
        this.defaultScore = defaultScore;
    }

    public int getScore() {
        return defaultScore;
    }
}
