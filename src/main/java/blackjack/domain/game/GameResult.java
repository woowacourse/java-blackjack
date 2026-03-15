package blackjack.domain.game;

public enum GameResult {
    PLAYER_BLACKJACK, PLAYER_WIN, DEALER_WIN, PUSH;

    public boolean isPlayerBlackjack() {
        return this == PLAYER_BLACKJACK;
    }

    public boolean isPlayerWin() {
        return this == PLAYER_WIN;
    }

    public boolean isDealerWin() {
        return this == DEALER_WIN;
    }

    public boolean isPush() {
        return this == PUSH;
    }
}
