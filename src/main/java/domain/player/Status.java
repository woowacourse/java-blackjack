package domain.player;

public enum Status {
    BLACKJACK_WIN(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final double multiplyRatio;

    Status(final double multiplyRatio) {
        this.multiplyRatio = multiplyRatio;
    }

    public double getMultiplyRatio() {
        return multiplyRatio;
    }
}
