package blackjack.domain.hand;

import blackjack.domain.card.CardBundle;

public abstract class Running extends Started {

    Running(CardBundle cardBundle) {
        super(cardBundle);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }
}
