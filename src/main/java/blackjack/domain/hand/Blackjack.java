package blackjack.domain.hand;

import blackjack.domain.card.CardBundle;

public final class Blackjack extends Finished {

    Blackjack(CardBundle cardBundle) {
        super(cardBundle);
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
