package domain;

public enum GameResult {

    WIN("승", 1),
    LOSE("패", -1),
    DRAW("무", 0),
    BLACKJACK("블랙잭", 1.5);

    private final String expression;
    private final double multiple;

    GameResult(String expression, double multiple) {
        this.expression = expression;
        this.multiple = multiple;
    }

    public String getExpression() {
        return expression;
    }

    public double getMultiple() {
        return multiple;
    }

}
