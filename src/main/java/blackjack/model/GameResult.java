package blackjack.model;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String status;

    GameResult(String status) {
        this.status = status;
    }

    public static GameResult calculateScore(Player player, Dealer dealer) {
        Score playerScore = player.getScore();
        Score dealerScore = dealer.getScore();

        if ((player.isBurst() && dealer.isBurst()) || (playerScore.isSame(dealerScore))) {
            return DRAW;
        }

        if (player.isBurst() || (!dealer.isBurst() && (playerScore.isLess(dealerScore)))) {
            return LOSE;
        }

        return WIN;
    }

    public String getStatus() {
        return status;
    }
}
