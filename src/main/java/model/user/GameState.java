package model.user;

public enum GameState {
    BLACKJACK(1.5),
    TIE(0),
    WIN(1),
    LOSE(-1);

    private final double profitRate;

    GameState(double profitRate) {
        this.profitRate = profitRate;
    }

    public boolean isBlackJack() {
        return BLACKJACK.equals(this);
    }

    public boolean isWin() {
        return WIN.equals(this);
    }

    public boolean isLose() {
        return LOSE.equals(this);
    }

    public long calculateMoney(final long bet) {
        return (long) (this.profitRate * bet);
    }
}
