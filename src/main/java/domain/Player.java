package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Card> cards;

    public Player() {
        cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public void saveCard(final Card card) {
        cards.add(card);
    }

    public int getTotalSize() {
        return cards.size();
    }

    public String getName() {
        return "pobi";
    }

    public List<Card> getCards() {
        return cards;
    }
}
