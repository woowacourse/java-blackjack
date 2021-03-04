package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player {

    private static final int BUST_LIMIT = 22;

    private final String name;
    private final List<Card> myCards;
    private boolean win = true;

    public Player(final String name) {
        this.name = name;
        this.myCards = new ArrayList<>();
    }

    public void receiveCard(final Card card) {
        myCards.add(card);
    }

    public String getName() {
        return name;
    }

    public int getCardCount() {
        return myCards.size();
    }

    public List<Card> getMyCards() {
        return Collections.unmodifiableList(myCards);
    }

    public boolean isBust() {
        return calculate() >= BUST_LIMIT;
    }

    public int calculate() {
        final int aceCount = (int) myCards.stream()
            .filter(Card::isAce)
            .count();
        if (aceCount == 0) {
            return calculateSingleCase();
        }
        return calculateMultipleCase(aceCount);
    }

    private int calculateSingleCase() {
        return myCards.stream()
            .mapToInt(Card::getCardNumber)
            .sum();
    }

    private int calculateMultipleCase(final int aceCount) {
        final List<Integer> possibleSum = new ArrayList<>();
        final int sumExceptAce = myCards.stream()
            .filter(card -> !card.isAce())
            .mapToInt(Card::getCardNumber)
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

    public void lose() {
        win = false;
    }

    public boolean getWin() {
        return win;
    }
}
