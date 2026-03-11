package blackjack.domain.result;

import blackjack.domain.hand.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class GameResultCalculator {

    public GameResult calculate(final Player player, final Dealer dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return GameResult.DRAW;
        }
        if (player.isBlackjack()) {
            return GameResult.BLACKJACK;
        }
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        return compare(player.calculateScore(), dealer.calculateScore());
    }

    private GameResult compare(final Score playerScore, final Score dealerScore) {
        int result = playerScore.compareTo(dealerScore);
        if (result > 0) {
            return GameResult.WIN;
        }
        if (result < 0) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }
}
