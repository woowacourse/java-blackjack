package domain;

public enum GameResult {

    WIN(1),
    LOSE(-1),
    DRAW(0),
    BLACKJACK(1.5);

    private final double multiple;

    GameResult(double multiple) {
        this.multiple = multiple;
    }

    public double getMultiple() {
        return multiple;
    }

}
