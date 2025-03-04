package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class ReceivedCards {

    private final List<Card> cards = new ArrayList<>();

    public ReceivedCards() {
    }

    public void receive(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }
}
