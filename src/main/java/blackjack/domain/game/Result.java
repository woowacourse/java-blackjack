package blackjack.domain.game;

import blackjack.domain.card.Hand;
import blackjack.domain.participant.Score;

public enum Result {
    BLACKJACK(1.5),
    WIN(1),
    DRAW(0),
    LOSE(-1);

    private static final Score BLACKJACK_SCORE = new Score(21);

    private final double yield;

    Result(double yield) {
        this.yield = yield;
    }

    public Money calculateYield(Money money) {
        return money.multiply(this.yield);
    }

    public static Result calculateResult(Hand playerHand, Hand dealerHand) {
        if (playerHand.isBlackJack() && !dealerHand.isBlackJack()) {
            return BLACKJACK;
        }
        if (playerHand.isBust()) {
            return LOSE;
        }
        if (dealerHand.isBust()) {
            return WIN;
        }
        return calculateResultByScore(playerHand.calculateScore(), dealerHand.calculateScore());
    }

    private static Result calculateResultByScore(Score playerScore, Score dealerScore) {
        if (playerScore.isGreaterThan(dealerScore)) {
            return WIN;
        }
        if (playerScore.isLessThan(dealerScore)) {
            return LOSE;
        }
        return DRAW;
    }

}
