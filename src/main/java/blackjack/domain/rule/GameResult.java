package blackjack.domain.rule;

public enum GameResult {

    BLACKJACK_WIN(1.5),
    PLAYER_WIN(1.0),
    PLAYER_LOSE(-1.0),
    PUSH(0.0);

    private final double leverage;

    GameResult(double leverage) {
        this.leverage = leverage;
    }

    public double getLeverage() {
        return leverage;
    }
}
