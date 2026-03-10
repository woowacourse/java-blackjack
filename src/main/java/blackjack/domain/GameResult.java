package blackjack.domain;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String status;

    GameResult(String status) {
        this.status = status;
    }

    public static GameResult getResult(Player player, Dealer dealer) {
        if (player.isBurst()) {
            return LOSE;
        }
        if (dealer.isBurst()) {
            return WIN;
        }

        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();
        if (playerScore < dealerScore) {
            return LOSE;
        }
        if (playerScore == dealerScore) {
            return DRAW;
        }
        return WIN;
    }

    public String getStatus() {
        return status;
    }
}
