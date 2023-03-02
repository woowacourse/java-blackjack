package blackjack.domain;

public enum State {
    BLACKJACK,
    PLAY,
    STOP,
    BUST;

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }

    public boolean isPlayable() {
        return this == PLAY;
    }

    public boolean isBust() {
        return this == BUST;
    }

    public boolean isNotBust() {
        return this != BUST;
    }
}
