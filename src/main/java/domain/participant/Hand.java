package domain.participant;

import domain.card.Card;
import domain.card.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int ACE_MIN_COUNT = 0;
    private static final int ADDITIONAL_ACE_VALUE = 10;
    private static final int BLACKJACK_LIMIT_VALUE = 21;

    private final List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public boolean isBust() {
        return calculateTotalScore() > BLACKJACK_LIMIT_VALUE;
    }

    public int getHandSize() {
        return hand.size();
    }

    public List<Card> getNowHand() {
        return Collections.unmodifiableList(hand);
    }

    public int calculateTotalScore() {
        int sum = hand.stream()
                .filter(card -> card.getRank() != Rank.ACE)
                .mapToInt(Card::getCardScore)
                .sum();

        int aceCount = (int) hand.stream()
                .filter(card -> card.getRank() == Rank.ACE)
                .count();

        return aceCalculate(sum, aceCount);
    }

    private int aceCalculate(int sum, int aceCount) {
        int totalSum = sum + (aceCount * Rank.ACE.getValue());
        if (aceCount > ACE_MIN_COUNT && totalSum + ADDITIONAL_ACE_VALUE <= BLACKJACK_LIMIT_VALUE) {
            return totalSum + ADDITIONAL_ACE_VALUE;
        }
        return totalSum;
    }
}
