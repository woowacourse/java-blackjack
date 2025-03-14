package result;

public enum GameStatus {
    BLACKJACK_WIN(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
    UNDECIDED(0.0);

    private final double payoutRate;

    GameStatus(double payoutRate) {
        this.payoutRate = payoutRate;
    }

    public boolean isEqualTo(GameStatus gameStatus) {
        return gameStatus == this;
    }

    public boolean isDecided() {
        return this != UNDECIDED;
    }

    public double getPayoutRate() {
        return payoutRate;
    }
}
