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
    protected double calculateDuelResult(CardHand dealerHand) {
        if (dealerHand.isBlackjack()) {
            return DRAW_BETTING_YIELD;
        }
        return BLACKJACK_WIN_BETTING_YIELD;
    }

    @Override
    public String toString() {
        return "Blackjack{" + "cardBundle=" + cardBundle + '}';
    }
}
