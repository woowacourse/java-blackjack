package blackjack.domain.state;

import blackjack.domain.card.CardBundle;

public class Bust extends FinishedState {

    private static final String INVALID_SCORE_EXCEPTION_MESSAGE = "21점을 초과하는 카드 패만 Bust 상태가 될 수 있습니다.";

    public Bust(CardBundle cardBundle) {
        super(cardBundle);
        validateScore(cardBundle);
    }

    private void validateScore(CardBundle cardBundle) {
        if (!cardBundle.isBust()) {
            throw new IllegalArgumentException(INVALID_SCORE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public double getBettingYieldVersus(CardHand targetHand) {
        return LOSE_BETTING_YIELD;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public String toString() {
        return "Bust{" + "cardBundle=" + cardBundle + '}';
    }
}
