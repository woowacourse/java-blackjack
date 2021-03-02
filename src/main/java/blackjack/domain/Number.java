package blackjack.domain;

public enum Number {
    ACE(1, 11),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private final int score;
    private final int secondScore;

    Number(int score, int secondScore) {
        this.score = score;
        this.secondScore = secondScore;
    }

    Number(int score) {
        this(score, 0);
    }
}
