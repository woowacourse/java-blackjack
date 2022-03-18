package blackjack.domain.hand;

import blackjack.domain.card.CardBundle;

public abstract class Running extends Started {

    Running(CardBundle cardBundle) {
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
}
