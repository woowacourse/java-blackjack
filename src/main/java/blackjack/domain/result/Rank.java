package blackjack.domain.result;

public enum Rank {

    WIN(1),
    DRAW(0),
    LOSE(-1),
    BLACKJACK(1.5);

    private final double rateOfReturn;

    Rank(final double rateOfReturn) {
        this.rateOfReturn = rateOfReturn;
    }

    public double getRateOfReturn() {
        return rateOfReturn;
    }
}
