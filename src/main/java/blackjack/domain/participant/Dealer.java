package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Dealer extends Participant {

    private static final int DEALER_BOUND = 16;

    public Dealer(final String name) {
        super(name);
    }

    public Card showFirstCard() {
        return hand.getFirstCard();
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() <= DEALER_BOUND;
    }
}
