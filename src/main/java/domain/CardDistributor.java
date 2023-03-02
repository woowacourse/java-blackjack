package domain;

import java.util.Collections;
import java.util.List;

public class CardDistributor {

    private final List<Card> cards;

    public CardDistributor(List<Card> cards) {
        this.cards = cards;
        shuffle(cards);
    }

    private void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }

    public Card pick() {
        return cards.remove(cards.size() - 1);
    }

    public int getDeckSize() {
        return cards.size();
    }

}
