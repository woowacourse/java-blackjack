package domain;

public enum Result {
    WIN(1),
    LOSE(-1),
    DRAW(0),
    BLACKJACK(1.5);

    private final double odds;

    Result(double odds) {
        this.odds = odds;
    }

    public int calculateWinningAmount(int bettingAmountValue) {
        return (int) (odds * bettingAmountValue);
    }
}
