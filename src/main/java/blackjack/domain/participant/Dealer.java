package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.card.Card;

public class Dealer extends Participant {

    private static final int DEALER_BOUND = 16;

    private final Deck deck;

    public Dealer(final String name, final Deck deck) {
        super(name);
        this.deck = deck;

        selfDraw();
        selfDraw();
    }

    public Card draw() {
        return deck.drawn();
    }

    public void selfDraw() {
        Card card = this.deck.drawn();
        hand.add(card);
    }

    public Card showFirstCard() {
        return hand.getFirstCard();
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() <= DEALER_BOUND;
    }
}
