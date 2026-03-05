package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {
    List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public Card draw() {
        return cards.removeFirst();
    }
}
