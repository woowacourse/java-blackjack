package domain;

public enum Result {

    DRAW(0), WIN(1), BLACKJACK(1.5), LOSE(-1);

    private final double dividend;

    Result(double dividend) {
        this.dividend = dividend;
    }

    public double getDividend() {
        return dividend;
    }
}
