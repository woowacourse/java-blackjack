package blackjack.domain.hand;

import blackjack.domain.card.CardBundle;

public final class Bust extends Finished {

    Bust(CardBundle cardBundle) {
        super(cardBundle);
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
    protected double calculateDuelResult(CardHand dealerHand) {
        return LOSE_BETTING_YIELD;
    }

    @Override
    public String toString() {
        return "Bust{" + "cardBundle=" + cardBundle + '}';
    }
}
