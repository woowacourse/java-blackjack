package blackjack.domain;

public class GameRule {

    public GameResult evaluateDealerWin(final Player player, final Dealer dealer) {
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

    public GameResult evaluatePlayerWin(final Player player, final Dealer dealer) {
        GameResult gameResult = evaluateDealerWin(player, dealer);

        if (gameResult == GameResult.WIN) {
            return GameResult.LOSE;
        }
        if (gameResult == GameResult.LOSE) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }
}
