package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BUST_LIMIT = 22;

    private final List<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public void add(final Card card) {
        hand.add(card);
    }

    public int calculateScore() {
        final int aceCount = (int) hand.stream()
                .filter(Card::isAce)
                .count();

        if (aceCount == 0) {
            return calculateSingleCase();
        }
        return calculateMultipleCase(aceCount);
    }

    private int calculateSingleCase() {
        return hand.stream()
                .mapToInt(Card::getCardValue)
                .sum();
    }

    private int calculateMultipleCase(final int aceCount) {
        final List<Integer> possibleSum = new ArrayList<>();
        final int sumExceptAce = hand.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getCardValue)
                .sum();

        for (final int aceSum : calculateAceSum(aceCount)) {
            possibleSum.add(sumExceptAce + aceSum);
        }
        return findMaxPossibleValue(possibleSum);
    }

    private List<Integer> calculateAceSum(final int aceCount) {
        int oneNormalRestExtra = CardNumber.ACE.getValue();
        int allExtra = CardNumber.ACE.getExtraValue();

        for (int i = 1; i < aceCount; i++) {
            oneNormalRestExtra += CardNumber.ACE.getExtraValue();
            allExtra += CardNumber.ACE.getExtraValue();
        }

        return new ArrayList<>(Arrays.asList(oneNormalRestExtra, allExtra));
    }

    private int findMaxPossibleValue(final List<Integer> possibleSum) {
        return possibleSum.stream()
                .filter(aceSum -> aceSum < BUST_LIMIT)
                .mapToInt(Integer::intValue)
                .max()
                .orElse(BUST_LIMIT);
    }

    public boolean isBust() {
        return calculateScore() >= BUST_LIMIT;
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }
}
