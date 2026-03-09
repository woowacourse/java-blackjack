package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private final List<Card> hand;

    public Hand() {
        hand = new ArrayList<>();
    }

    public void add(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return List.copyOf(hand);
    }

    public List<String> toStringList() {
        return hand.stream()
                .map(Card::toString)
                .toList();
    }

}
