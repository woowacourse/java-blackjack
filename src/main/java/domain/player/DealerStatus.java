package domain.player;

public enum DealerStatus {
    WIN(1),
    DRAW(0),
    LOSE(-1),
    BLACKJACK_LOSE(-1.5);

    private final double multiply;

    DealerStatus(final double multiply) {
        this.multiply = multiply;
    }

    public double getMultiply() {
        return multiply;
    }
}
