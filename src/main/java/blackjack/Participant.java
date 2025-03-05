package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private final List<Card> hand;

    public Participant(List<Card> hand) {
        this.hand = new ArrayList<>(hand);
    }

    public void receiveHand(Card card) {
        hand.add(card);
    }

    public int getTotal() {
        int total = 0;
        for (Card card : hand) {
            CardValue cardValue = card.getCardValue();
            total += cardValue.getDefaultValue();
        }
        if (total <= 11 && hasAce()) {
            total += 10;
        }
        return total;
    }

    private boolean hasAce() {
        return hand.stream()
                .anyMatch(card -> card.getCardValue() == CardValue.ACE);
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }
}
