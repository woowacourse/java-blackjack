package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    private Deck(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Deck of(final CardGenerator cardGenerator) {
        return new Deck(cardGenerator.generate());
    }

    public Card pollCard() {
        return cards.removeFirst();
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

}
