package domain.generator;

import domain.card.Card;
import domain.card.Deck;

public class CardRandomGenerator implements RandomGenerator<Card> {
    private final Deck deck = new Deck();

    public Card generate() {
        return deck.draw();
    }
}
