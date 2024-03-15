package blackjack.domain.card;

public enum Denomination {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K");

    private final Score score;
    private final String expression;

    Denomination(final int score, final String expression) {
        this.score = Score.valueOf(score);
        this.expression = expression;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public Score getScore() {
        return score;
    }

    public String getExpression() {
        return expression;
    }
}
