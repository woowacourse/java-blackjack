package blackjack.domain.game;

public enum Result {
    BLACKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private final double yield;

    Result(double yield) {
        this.yield = yield;
    }

    public Money calculateYield(Money money) {
        return money.multiply(this.yield);
    }
}
