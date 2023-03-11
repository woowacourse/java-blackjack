package blackjack.domain;

public enum WinResult {

    WIN,
    PUSH,
    LOSE;

    public WinResult counter() {
        if (this == WIN) {
            return LOSE;
        }

        if (this == LOSE) {
            return WIN;
        }

        return PUSH;
    }
}
