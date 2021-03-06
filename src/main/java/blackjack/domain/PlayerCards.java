package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlayerCards {

    private static final int BUST_LIMIT = 22;
    private final List<Card> myCards;

    public PlayerCards() {
        this.myCards = new ArrayList<>();
    }

    public void receiveCard(final Card card) {
        myCards.add(card);
    }

    public int getCardCount() {
        return myCards.size();
    }

    public List<Card> toList() {
        return Collections.unmodifiableList(myCards);
    }

    public int calculate() {
        final int aceCount = (int) myCards.stream()
            .map(Card::getCardNumber)
            .filter(CardNumber::isAce)
            .count();
        if (aceCount == 0) {
            return calculateSingleCase();
        }
        return calculateMultipleCase(aceCount);
    }

    private int calculateSingleCase() {
        return myCards.stream()
            .map(Card::getCardNumber)
            .mapToInt(CardNumber::getValue)
            .sum();
    }

    private int calculateMultipleCase(final int aceCount) {
        final List<Integer> possibleSum = new ArrayList<>();
        final int sumExceptAce = myCards.stream()
            .map(Card::getCardNumber)
            .filter(cardNumber -> !cardNumber.isAce())
            .mapToInt(CardNumber::getValue)
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

}
