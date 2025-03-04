package blackjack.model;

import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }


    public int getCardCount() {
        return cards.size();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
