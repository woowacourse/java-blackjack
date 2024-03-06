package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.card.Card;
import java.util.List;

public abstract class Participant {

    protected final String name;
    protected final Hand hand;

    public Participant(final String name) {
        this.name = name;
        this.hand = new Hand();
    }

    abstract boolean canReceiveCard();

    public void draw(Deck deck) {
        Card card = deck.drawn();
        hand.add(card);
    }

    public List<Card> getHandCards() {
        return hand.getCards();
    }
}
