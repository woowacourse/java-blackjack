package blackjack.model.participant;

import blackjack.model.deck.Card;
import blackjack.model.deck.Deck;
import java.util.List;

public class Dealer extends Participant {
    private static final int HITTABLE_THRESHOLD = 16;

    private final Deck deck;

    public Dealer(final Deck deck) {
        this.deck = deck.makeDeck();
    }

    @Override
    public boolean canHit() {
        return hand.calculateScore() <= HITTABLE_THRESHOLD;
    }

    public List<Card> distributeInitialCard() {
        return List.of(deck.distribute(), deck.distribute());
    }

    public Card distributeCard() {
        return deck.distribute();
    }

    public List<Card> openFirstCard() {
        final List<Card> cards = hand.getCards();
        return List.of(cards.get(0));
    }
}
