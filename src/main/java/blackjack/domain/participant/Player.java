package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.card.Card;
import java.util.List;

public class Player implements Participant {

    private static final int BLACKJACK_BOUND = 21;

    private final String name;
    private final Hand hand;

    public Player(final String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public List<Card> getHandCards() {
        return this.hand.getCards();
    }

    @Override
    public boolean canReceiveCard() {
        return hand.calculateScore() < BLACKJACK_BOUND;
    }

    @Override
    public void draw(final Deck deck) {
        Card card = deck.drawn();
        hand.add(card);
    }
}
