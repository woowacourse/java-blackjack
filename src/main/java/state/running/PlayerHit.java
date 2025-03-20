package state.running;

import card.Card;
import card.CardHand;
import state.State;
import state.finished.BlackJack;
import state.finished.Bust;
import state.finished.Stay;

public final class PlayerHit extends Running {
    private PlayerHit(final CardHand cardHand) {
        super(cardHand);
    }

    public static State initialState(final CardHand cardHand) {
        if (cardHand.isBlackJack()) {
            return new BlackJack(cardHand);
        }
        return new PlayerHit(cardHand);
    }

    @Override
    public State receiveCard(final Card card) {
        cardHand.add(card);
        if (cardHand().isBust()) {
            return new Bust(cardHand);
        }
        return this;
    }

    @Override
    public State stay() {
        return new Stay(cardHand);
    }
}
