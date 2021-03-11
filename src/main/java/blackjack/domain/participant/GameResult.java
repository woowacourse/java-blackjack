package blackjack.domain.participant;

public enum GameResult {
    WIN("승"),
    LOSE("패");

    private final String resultMessage;

    GameResult(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public static String getPlayerGameResultAgainstDealer(Player player, Dealer dealer) {
        int playerScore = player.calculateCardsScoreResult();
        int dealerScore = dealer.calculateCardsScoreResult();

        if (player.isBust() || (!dealer.isBust() && dealerScore >= playerScore)) {
            return GameResult.LOSE.resultMessage;
        }

        return GameResult.WIN.resultMessage;
    }
}