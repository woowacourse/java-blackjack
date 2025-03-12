package blackjack.domain;

public enum GameResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String status;

    GameResult(String status) {
        this.status = status;
    }

    public static GameResult checkDealerWin(final Player player, final Dealer dealer) {
        int playerScore = player.calculateTotalCardScore();
        int dealerScore = dealer.calculateTotalCardScore();

        if (player.isBust()) {
            return GameResult.WIN;
        }
        if (dealer.isBust()) {
            return GameResult.LOSE;
        }

        if (dealerScore > playerScore) {
            return GameResult.WIN;
        }
        if (dealerScore < playerScore) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    public static GameResult checkPlayerWin(final Player player, final Dealer dealer) {
        GameResult gameResult = checkDealerWin(player, dealer);

        if (gameResult == GameResult.WIN) {
            return GameResult.LOSE;
        }
        if (gameResult == GameResult.LOSE) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    public String getStatus() {
        return status;
    }
}
