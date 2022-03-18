package blackjack.domain.hand;

import blackjack.domain.card.CardBundle;

public abstract class Running extends Started {

    private final static String STILL_RUNNING_EXCEPTION_MESSAGE = "아직 확장되지 않은 패입니다.";

    protected Running(CardBundle cardBundle) {
        super(cardBundle);
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final boolean isBlackjack() {
        return false;
    }

    @Override
    public final boolean isBust() {
        return false;
    }

    @Override
    public double profit(CardHand dealerHand, int money) {
        throw new IllegalStateException(STILL_RUNNING_EXCEPTION_MESSAGE);
    }
}
