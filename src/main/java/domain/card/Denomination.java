package domain.card;

public enum Denomination {
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

    private final String expression;
    private final int score;

    Denomination(String expression, int score) {
        this.expression = expression;
        this.score = score;
    }

    public boolean isAce() {
        return this == ACE;
    }

    public String getExpression() {
        return this.expression;
    }

    public int getScore() {
        return this.score;
    }
}
