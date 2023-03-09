package domain.game;

public enum ResultStatus {
    WIN,
    WIN_BLACKJACK,
    DRAW,
    LOSE;
    
    public static ResultStatus of(boolean isWin, boolean isDraw, boolean isBlackJack) {
        if (isWin && isBlackJack) {
            return WIN_BLACKJACK;
        }
        if (isWin) {
            return WIN;
        }
        if (isDraw) {
            return DRAW;
        }
        return LOSE;
    }
}
