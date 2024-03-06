package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.card.Card;

public class Dealer extends Participant {

    private static final int DEALER_BOUND = 16;

    public Dealer(final String name, final Deck deck) {
        super(name);

        draw(deck);
        draw(deck);
    }

    public Card showFirstCard() {
        return hand.getFirstCard();
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() <= DEALER_BOUND;
    }
}
