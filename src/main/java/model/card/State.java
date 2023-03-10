package model.card;

public enum State {
    BLACKJACK,
    TIE,
    WIN,
    LOSE;

    public boolean isBlackJack() {
        return BLACKJACK.equals(this);
    }

    public boolean isWin() {
        return WIN.equals(this);
    }

    public boolean isLose() {
        return LOSE.equals(this);
    }
}
