package domain;

public enum Result {
    BLACKJACK(1.5), WIN(1.0), DRAW(0), LOSE(-1);
    private final double odds;

    Result(double odd) {
        this.odds = odd;
    }

    public double getOdds() {
        return odds;
    }
}
