package blackjack.model;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String status;

    GameResult(String status) {
        this.status = status;
    }

    public static GameResult getResult(int playerScore, int dealerScore) {
        if (playerScore > dealerScore) {
            return WIN;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return LOSE;
    }

    public String getStatus() {
        return status;
    }
}
