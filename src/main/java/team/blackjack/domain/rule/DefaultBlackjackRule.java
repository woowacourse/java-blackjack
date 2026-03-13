package team.blackjack.domain.rule;

import team.blackjack.domain.Hand;
import team.blackjack.domain.Result;

public class DefaultBlackjackRule implements BlackjackRule {
    private static final int DEALER_STAND_SCORE = 17;

    @Override
    public boolean isDealerMustDraw(int score) {
        return score < DEALER_STAND_SCORE;
    }

    @Override
    public Result judgePlayerResult(Hand playerHand, Hand dealerHand) {
        if (playerHand.isBust()) {
            return Result.LOSE;
        }
        if (dealerHand.isBust()) {
            return Result.WIN;
        }
        if (playerHand.isBlackjack() && !dealerHand.isBlackjack()) {
            return Result.BLACKJACK;
        }
        if (!playerHand.isBlackjack() && dealerHand.isBlackjack()) {
            return Result.LOSE;
        }

        int playerScore = playerHand.getScore();
        int dealerScore = dealerHand.getScore();

        if (playerScore > dealerScore) {
            return Result.WIN;
        }
        if (playerScore < dealerScore) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }
}
