package model.gameresult;

public enum GameResult {
    BLACKJACK_WIN("블랙잭 승리"),
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String message;

    GameResult(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
