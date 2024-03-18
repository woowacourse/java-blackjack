package blackjack.domain.cardgame;

public enum Status {
    WIN(1),
    PUSH(0),
    LOSE(-1),
    BLACKJACK(1.5),
    ;

    private final double multiplier;

    Status(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}