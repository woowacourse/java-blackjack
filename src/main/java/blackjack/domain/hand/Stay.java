package blackjack.domain.hand;

import blackjack.domain.card.CardBundle;

public final class Stay extends Finished {

    Stay(CardBundle cardBundle) {
        super(cardBundle);
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
