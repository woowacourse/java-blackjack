package blackjack.domain.card;

import java.util.LinkedList;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    public Deck(DeckGenerator deckGenerator) {
        this.cards = new LinkedList<>(deckGenerator.generate());
    }

    public CardGroup drawFirstCardGroup() {
        return new CardGroup(cards.poll(), cards.poll());
    }

    public Card draw() {
        return cards.poll();
    }
}
