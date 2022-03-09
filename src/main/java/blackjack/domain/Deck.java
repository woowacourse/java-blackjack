package blackjack.domain;

import java.util.LinkedList;
import java.util.Queue;

public class Deck {

    private final Queue<Card> cards;

    public Deck(CardGenerator cardGenerator) {
        this.cards = new LinkedList<>(cardGenerator.generate());
    }

    public Queue<Card> getCards() {
        return cards;
    }
}
