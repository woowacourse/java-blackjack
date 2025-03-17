package domain;

public enum GameResult {
    WIN(1),
    LOSE(-1),
    DRAW(0),
    BLACKJACK(1.5);

    private double dividends;

    GameResult(double dividends) {
        this.dividends = dividends;
    }

    public double getDividends() {
        return this.dividends;
    }
}
