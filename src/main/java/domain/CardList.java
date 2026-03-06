package domain;

import java.util.ArrayList;
import java.util.List;

public class CardList {
    private final List<Card> cards;

    CardList() {
        this.cards = new ArrayList<>();
    }

    public Card draw() {
        Card card = new Card();
        return card;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int get(int index) {
        return cards.get(index).getCard();
    }

    public int size() {
        return cards.size();
    }
}
