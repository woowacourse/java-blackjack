package blackjack.domain.result;

import blackjack.domain.hand.Score;

public class GameResultCalculator {

    public GameResult calculate(final Score playerScore, final Score dealerScore) {
        if (playerScore.isBust()) {
            return GameResult.LOSE;
        }
        if (dealerScore.isBust()) {
            return GameResult.WIN;
        }
        return compare(playerScore, dealerScore);
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
