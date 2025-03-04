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

    public int calculateTotalPoint() {
        return cards.stream().mapToInt(Card::getPoint).sum();
    }

    public boolean isBust() {
        return calculateTotalPoint() > 21;
    }
}
