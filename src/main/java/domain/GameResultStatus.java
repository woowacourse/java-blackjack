package domain;

public enum GameResultStatus {
    BLACKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private double payout;

    GameResultStatus(double payout) {
        this.payout = payout;
    }

    public double getPayout() {
        return payout;
    }
}
