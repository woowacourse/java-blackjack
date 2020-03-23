package domain;

public enum PlayerResult {
    BLACKJACKWIN(1.5),
    DRAW(0),
    WIN(1.0),
    LOSE(-1.0);

    private double resultState;

    PlayerResult(double resultState) {
        this.resultState = resultState;
    }

    public double getResultState() {
        return resultState;
    }
}
