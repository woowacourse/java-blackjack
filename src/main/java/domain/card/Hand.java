package domain.card;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private static final int BONUS_THRESHOLD = 11;
    private static final int ACE_BONUS = 10;

    private final List<Card> hand;

    public Hand(final List<Card> hand) {
        this.hand = new ArrayList<>(hand);
    }

    public void addCard(final Card card) {
        hand.add(card);
    }

    public int calculateSum() {
        int sum = hand.stream()
                .mapToInt(Card::getScore)
                .sum();
        if (sum <= BONUS_THRESHOLD && hasA()) {
            sum += ACE_BONUS;
        }
        return sum;
    }

    private boolean hasA() {
        return hand.stream()
                .anyMatch(Card::isA);
    }

    public List<Card> getHand() {
        return hand;
    }
}
