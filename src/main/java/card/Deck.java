package card;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = cards;
    }

    private Card drawCard() {
        return cards.removeLast();
    }

    public List<Card> drawCards(int count) {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cards.add(drawCard());
        }

        return cards;
    }

    public int size() {
        return cards.size();
    }
}
