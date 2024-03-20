package resultJudge;

public enum GameResult {

    PLAYER_BLACKJACK(1.5),
    PLAYER_BUST(-1),
    PLAYER_LOSE(-1),
    PLAYER_PUSH(0),
    PLAYER_WIN(1);

    final double moneyReturnPercent;

    GameResult(double moneyReturnPercent) {
        this.moneyReturnPercent = moneyReturnPercent;
    }

    public double getMoneyReturnPercent() {
        return moneyReturnPercent;
    }
}
