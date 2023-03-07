package domain;

public enum GameResult {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String expression;

    GameResult(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression + " ";
    }

}
