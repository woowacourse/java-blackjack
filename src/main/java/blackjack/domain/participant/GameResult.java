package blackjack.domain.participant;

public enum GameResult {
    WIN("승"),
    LOSE("패");

    private final String resultMessage;

    GameResult(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public static String getWinLoseResult(Player player, Dealer dealer) {
        int playerScore = player.calculateResult();
        int dealerScore = dealer.calculateResult();

        if (player.isBurst() || dealerScore >= playerScore) {
            return GameResult.LOSE.resultMessage;
        }

        return GameResult.WIN.resultMessage;
    }
}
