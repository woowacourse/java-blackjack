package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    protected final List<Card> hand;

    protected Participant(List<Card> hand) {
        this.hand = new ArrayList<>(hand);
    }

    public abstract boolean shouldHit();

    public void receiveHand(Card card) {
        hand.add(card);
    }

    public int getTotal() {
        int total = sumHandWithoutAce();
        if (hasAce()) {
            return total + calculateAceValue(total);
        }
        return total;
    }

    private int sumHandWithoutAce() {
        List<Card> handWithoutAce = getHandWithoutAce();
        int total = 0;
        for (Card card : handWithoutAce) {
            total += card.getCardValue().getDefaultValue();
        }
        return total;
    }

    private List<Card> getHandWithoutAce() {
        return hand.stream()
                .filter(card -> card.getCardValue() != CardValue.ACE)
                .toList();
    }

    private boolean hasAce() {
        return hand.stream()
                .anyMatch(card -> card.getCardValue() == CardValue.ACE);
    }

    private int calculateAceValue(int total) {
        int aceCount = getAceCountInHand();
        int aceValue = 0;
        if (total <= 10) {
            aceValue += 11;
            aceCount--;
        }
        return aceValue + (aceCount * CardValue.ACE.getDefaultValue());
    }

    private int getAceCountInHand() {
        return (int) hand.stream()
                .filter(card -> card.getCardValue() == CardValue.ACE)
                .count();
    }

    public boolean isBlackjack() {
        return hand.size() == 2 && getTotal() == 21;
    }

    public boolean isBust() {
        return 21 < getTotal();
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }
}
