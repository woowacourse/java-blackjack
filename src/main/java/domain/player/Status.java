package domain.player;

public enum Status {
    BLACKJACK_WIN(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final double multiply;

    Status(final double multiply) {
        this.multiply = multiply;
    }

    public double getMultiply() {
        return multiply;
    }
}
