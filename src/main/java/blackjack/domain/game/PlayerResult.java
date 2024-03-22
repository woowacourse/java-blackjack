package blackjack.domain.game;

public enum PlayerResult {
    BLACKJACK_WIN(1.5),
    WIN(1),
    PUSH(0),
    LOSE(-1);

    private final double operator;

    PlayerResult(double operator) {
        this.operator = operator;
    }

    public double operator() {
        return operator;
    }
}
