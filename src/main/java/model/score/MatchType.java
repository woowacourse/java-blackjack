package model.score;

public enum MatchType {
    WIN(1),
    LOSE(-1),
    DRAW(0);

    private final int condition;

    MatchType(int condition) {
        this.condition = condition;
    }
}
