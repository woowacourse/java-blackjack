package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

class Hand {

    private final List<Card> cards = new ArrayList<>();

    public List<Card> getCards() {
        return cards;
    }

    public void add(Card card) {
        cards.add(card);
    }
}
