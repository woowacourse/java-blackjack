package blackjack.domain.card;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Deck {

    private final LinkedList<Card> deck;

    public Deck(List<Card> deck) {
        this.deck = new LinkedList<>(deck);
        Collections.shuffle(this.deck);
    }

    public Card draw() {
        Card card = deck.getFirst();
        deck.removeFirst();
        return card;
    }

    public int getSize() {
        return deck.size();
    }
}
