package domain.game;

import domain.card.Hand;
import domain.card.Score;

public class Referee {
    public Result judgePlayerResult(final Hand playerHand, final Hand dealerHand) {
        if (playerHand.isBlackjack() && dealerHand.isBlackjack()) {
            return Result.PUSH;
        }
        if (playerHand.isBlackjack()) {
            return Result.BLACKJACK_WIN;
        }
        if (dealerHand.isBlackjack()) {
            return Result.LOSE;
        }
        return judgePlayerResult(playerHand.getSumOfScores(), dealerHand.getSumOfScores());
    }

    public Result judgePlayerResult(final Score playerScore, final Score dealerScore) {
        if (dealerScore.isBust()) {
            return Result.WIN;
        }
        if (playerScore.isBust() || dealerScore.isGreaterThan(playerScore)) {
            return Result.LOSE;
        }
        if (playerScore.isGreaterThan(dealerScore)) {
            return Result.WIN;
        }
        return Result.PUSH;
    }
}
