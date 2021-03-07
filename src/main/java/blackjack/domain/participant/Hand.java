package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BUST_LIMIT = 22;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        final int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        if (aceCount == 0) {
            return calculateSingleCase();
        }
        return calculateMultipleCase(aceCount);
    }

    private int calculateSingleCase() {
        return cards.stream()
                .mapToInt(card -> card.getCardLetter().getValue())
                .sum();
    }

    private int calculateMultipleCase(final int aceCount) {
        final List<Integer> possibleSum = new ArrayList<>();
        final int sumExceptAce = cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(card -> card.getCardLetter().getValue())
                .sum();

        for (final int aceSum : calculateAceSum(aceCount)) {
            possibleSum.add(sumExceptAce + aceSum);
        }
        return findMaxPossibleValue(possibleSum);
    }

    private List<Integer> calculateAceSum(final int aceCount) {
        int oneNormalRestExtra = CardLetter.ACE.getValue();
        int allExtra = CardLetter.ACE.getExtraValue();

        for (int i = 1; i < aceCount; i++) {
            oneNormalRestExtra += CardLetter.ACE.getExtraValue();
            allExtra += CardLetter.ACE.getExtraValue();
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

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
