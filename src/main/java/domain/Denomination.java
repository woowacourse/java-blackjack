package domain;

public enum Denomination {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String expression;

    Denomination(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return this.expression;
    }
}
