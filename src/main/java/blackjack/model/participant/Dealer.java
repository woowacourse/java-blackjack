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

    public boolean shouldHit() {
        return hand.calculateScore() <= HITTABLE_THRESHOLD;
    }

    public List<Card> distributeInitialCard() {
        return List.of(deck.drawn(), deck.drawn());
    }

    public Card distributeCard() {
        return deck.drawn();
    }

    public List<Card> openFirstCard() {
        final List<Card> cards = hand.getCards();
        return List.of(cards.get(0));
    }
}
