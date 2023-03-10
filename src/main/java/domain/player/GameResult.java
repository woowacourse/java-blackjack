package domain.player;

public enum GameResult {

    BLACKJACK_WIN(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1),
    ;

    private final double revenueRate;

    GameResult(final double revenueRate) {
        this.revenueRate = revenueRate;
    }

    public double revenueRate() {
        return revenueRate;
    }
}
