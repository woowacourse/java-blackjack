package player;

import java.util.ArrayList;
import java.util.List;

import card.Card;

public class Hand {
    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
