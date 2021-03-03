package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private static final int BUST_LIMIT = 22;

    private final String name;
    private final List<Card> myCards;

    public Player(final String name) {
        this.name = name;
        this.myCards = new ArrayList<>();
    }

    public void receiveCard(final Card card) {
        myCards.add(card);
    }

    public int getCardCount() {
        return myCards.size();
    }

    public boolean isBust() {
        return calculate() >= BUST_LIMIT;
    }

    public int calculate() {
        final int aceCount = (int) myCards.stream()
                .filter(Card::isAce)
                .count();
        if (aceCount < 2) {
            return calculateSingleCase();
        }
        return calculateMultipleCase(aceCount);
    }

    private int calculateSingleCase() {
        return myCards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    private int calculateMultipleCase(int aceCount) {
        final List<Integer> possibleSum = new ArrayList<>();
        final int sumExceptAce = myCards.stream()
                            .filter(card -> !card.isAce())
                            .mapToInt(Card::getCardNumber)
                            .sum();

        for (int aceSum : calculateAceSum(aceCount)) {
            possibleSum.add(sumExceptAce + aceSum);
        }
        return findMaxPossibleValue(possibleSum);
    }

    private List<Integer> calculateAceSum(int aceCount) {
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
}
