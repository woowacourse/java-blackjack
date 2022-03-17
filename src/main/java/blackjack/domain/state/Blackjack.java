package blackjack.domain.state;

import blackjack.domain.card.CardBundle;
import blackjack.domain.game.DuelResult;

public class Blackjack extends FinishedState {

    private static final String INVALID_CARD_HAND_EXCEPTION_MESSAGE = "해당 카드 패는 블랙잭이 아닙니다.";

    public Blackjack(CardBundle cardBundle) {
        super(cardBundle);
        validateBlackjack(cardBundle);
    }

    private void validateBlackjack(CardBundle cardBundle) {
        if (!cardBundle.isBlackjack()) {
            throw new IllegalArgumentException(INVALID_CARD_HAND_EXCEPTION_MESSAGE);
        }
    }

    public DuelResult getDuelResultOf(CardHand targetHand) {
        if (targetHand.isBlackjack()) {
            return DuelResult.DRAW;
        }
        return DuelResult.BLACKJACK_WIN;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public String toString() {
        return "Blackjack{" + "cardBundle=" + cardBundle + '}';
    }
}
