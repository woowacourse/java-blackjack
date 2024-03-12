package domain.player;

public enum PlayerResult {
    WIN,
    LOSE,
    TIE,
    NONE;

    public static PlayerResult reverse(final PlayerResult playerResult) {
        if (playerResult == WIN) {
            return LOSE;
        }
        if (playerResult == LOSE) {
            return WIN;
        }
        return playerResult;
    }
}
