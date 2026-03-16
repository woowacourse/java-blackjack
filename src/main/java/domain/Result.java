package domain;

public enum Result {
    BLACKJACK(1.5), WIN(1.0), DRAW(0), LOSE(-1);
    private final double odd;

    Result(double odd) {
        this.odd = odd;
    }

    public double getOdd() {
        return odd;
    }
}
