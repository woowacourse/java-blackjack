package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.CardValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int BLACKJACK_HAND_COUNT = 2;
    private static final int BLACKJACK_VALUE_TOTAL = 21;
    private static final int SOFT_ACE_VALUE = 11;
    private static final int SOFT_HAND_AVAILABLE_THRESHOLD = 10;

    protected final List<Card> hand;

    protected Participant(List<Card> hand) {
        this.hand = new ArrayList<>(hand);
    }

    public abstract boolean shouldHit();

    public void receiveHand(Card card) {
        if (BLACKJACK_VALUE_TOTAL <= getTotal()) {
            throw new IllegalArgumentException("더 이상 카드를 받을 수 없습니다.");
        }
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
        if (total <= SOFT_HAND_AVAILABLE_THRESHOLD) {
            aceValue += SOFT_ACE_VALUE;
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
        return hand.size() == BLACKJACK_HAND_COUNT
                && getTotal() == BLACKJACK_VALUE_TOTAL;
    }

    public boolean isBust() {
        return BLACKJACK_VALUE_TOTAL < getTotal();
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }
}
