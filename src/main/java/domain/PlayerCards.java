package domain;

import java.util.ArrayList;
import java.util.List;

public class PlayerCards {
    private final List<Card> cards;

    public PlayerCards() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> toList() {
        return List.copyOf(cards);
    }
}
