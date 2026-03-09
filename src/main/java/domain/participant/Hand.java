package domain.participant;

import domain.card.Card;
import domain.card.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int ACE_MIN_VALUE = 1;
    private static final int BLACKJACK_LIMIT_VALUE = 21;

    private final List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public boolean isBust() {
        return calculateTotalScore() > 21;
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

        int aCount = (int) hand.stream()
                .filter(card -> card.getRank() == Rank.ACE)
                .count();

        return aceCalculate(sum, aCount);
    }

    private int aceCalculate(int sum, int aCount) {
        int totalSum = sum + (aCount * Rank.ACE.getValue());
        while (aCount > 0 && totalSum > BLACKJACK_LIMIT_VALUE) {
            totalSum -= (Rank.ACE.getValue() - ACE_MIN_VALUE);
            aCount--;
        }
        return totalSum;
    }
}
