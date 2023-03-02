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
}
