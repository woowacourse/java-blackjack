package domain.card;

import java.util.List;

public class Hand {
    private final List<Card> hand;

    public Hand(List<Card> hand) {
        this.hand = hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }
}
