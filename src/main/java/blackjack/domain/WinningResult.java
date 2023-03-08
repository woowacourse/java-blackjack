package blackjack.domain;

public enum WinningResult {

    WIN,
    LOSE,
    PUSH;

    public boolean isWin() {
        return this == WIN;
    }

    public boolean isLose() {
        return this == LOSE;
    }

    public boolean isPush() {
        return this == PUSH;
    }
}
