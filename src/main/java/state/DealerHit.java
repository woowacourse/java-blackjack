package state;

import card.Card;
import card.CardHand;

public final class DealerHit extends Running {
    private DealerHit(final CardHand cardHand) {
        super(cardHand);
    }

    public static State initialState(final CardHand cardHand) {
        if (cardHand.isBlackJack()) {
            return new BlackJack(cardHand);
        }
        if (cardHand.isDealerStay()) {
            return new Stay(cardHand);
        }
        return new DealerHit(cardHand);
    }

    @Override
    public State receiveCard(final Card card) {
        cardHand.add(card);
        if (cardHand().isBust()) {
            return new Bust(cardHand);
        }
        if (cardHand.isDealerStay()) {
            return new Stay(cardHand);
        }
        return this;
    }

    @Override
    public State stay() {
        return new Stay(cardHand);
    }
}
