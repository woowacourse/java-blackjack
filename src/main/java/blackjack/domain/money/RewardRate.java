package blackjack.domain.money;

public enum RewardRate {
    WIN(1),
    LOSE(-1),
    DRAW(0),
    BLACKJACK(1.5);

    private final double rate;

    RewardRate(double rate) {
        this.rate = rate;
    }

    public double get() {
        return rate;
    }
}
