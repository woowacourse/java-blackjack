package blackjack.domain.state;

import blackjack.domain.card.CardBundle;
import blackjack.domain.game.Score;

public class Stay extends FinishedState {

    private static final String BLACKJACK_NOT_ALLOWED_EXCEPTION_MESSAGE = "블랙잭은 Stay 상태가 될 수 없습니다.";
    private static final String OVER_21_EXCEPTION_MESSAGE = "21점 이하의 카드 패만 Stay 상태가 될 수 있습니다.";

    public Stay(CardBundle cardBundle) {
        super(cardBundle);
        validateNotBlackjack(cardBundle);
        validateScore(cardBundle);
    }

    private void validateNotBlackjack(CardBundle cardBundle) {
        if (cardBundle.isBlackjack()) {
            throw new IllegalArgumentException(BLACKJACK_NOT_ALLOWED_EXCEPTION_MESSAGE);
        }
    }

    private void validateScore(CardBundle cardBundle) {
        if (cardBundle.isBust()) {
            throw new IllegalArgumentException(OVER_21_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public double getBettingYieldVersus(CardHand targetHand) {
        if (targetHand.isBlackjack()) {
            return LOSE_BETTING_YIELD;
        }
        if (targetHand.isBust()) {
            return WIN_BETTING_YIELD;
        }
        return getCompareResultOf(targetHand.getCardBundle());
    }

    private double getCompareResultOf(CardBundle targetCardBundle) {
        int compareResult = getScoreOf(cardBundle).compareTo(getScoreOf(targetCardBundle));

        if (compareResult > 0) {
            return WIN_BETTING_YIELD;
        }
        if (compareResult < 0) {
            return LOSE_BETTING_YIELD;
        }
        return DRAW_BETTING_YIELD;
    }

    private Score getScoreOf(CardBundle cardBundle) {
        return cardBundle.getScore();
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public String toString() {
        return "Stay{" + "cardBundle=" + cardBundle + '}';
    }
}
