package blackjack.model;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String status;

    GameResult(String status) {
        this.status = status;
    }

    public static GameResult getResult(Player player, Dealer dealer) {
        int playerScore = player.getScore();
        int dealerScore = dealer.getScore();

        if ((player.isBurst() && dealer.isBurst())) {
            return DRAW;
        }

        if (player.isBurst() || (!dealer.isBurst() && (playerScore < dealerScore))) {
            return LOSE;
        }

        return WIN;
    }

    public String getStatus() {
        return status;
    }
}
