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
        int point = calculateTotalDefaultPoint();
        int aceCount = aceCount();
        for (int i = 0; i < aceCount; i++) {
            if (!isBust(point + 10)) {
                point += 10;
            }
        }
        return point;
    }

    private int calculateTotalDefaultPoint() {
        return cards.stream().mapToInt(Card::getPoint).sum();
    }

    private int aceCount() {
        return Math.toIntExact(cards.stream()
                .filter(card -> card instanceof AceCard)
                .count());
    }

    public boolean isBust(int point) {
        return point > 21;
    }

    public Card get(int index) {
        return cards.get(0);
    }
}
