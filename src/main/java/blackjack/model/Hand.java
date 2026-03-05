package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }
}
