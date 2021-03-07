package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Player {
    private static final int BUST_LIMIT = 22;

    private final String name;
    protected final List<Card> cards;
    private boolean win = true;

    public Player(final String name) {
        this.name = validateName(name);
        this.cards = new ArrayList<>();
    }

    private String validateName(String name) {

        return name;
    }

    public void receiveCard(final Card card) {
        cards.add(card);
    }

    public String getName() {
        return name;
    }

    public int getCardCount() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean isBust() {
        return calculate() >= BUST_LIMIT;
    }

    public int calculate() {
        final int aceCount = (int) cards.stream()
                .filter(card -> card.getCardValue().isAce())
                .count();
        if (aceCount == 0) {
            return calculateSingleCase();
        }
        return calculateMultipleCase(aceCount);
    }

    private int calculateSingleCase() {
        return cards.stream()
                .mapToInt(card -> card.getCardValue().getValue())
                .sum();
    }

    private int calculateMultipleCase(final int aceCount) {
        final List<Integer> possibleSum = new ArrayList<>();
        final int sumExceptAce = cards.stream()
                .filter(card -> !card.getCardValue().isAce())
                .mapToInt(card -> card.getCardValue().getValue())
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
        this.win = false;
    }

    public boolean getWin() {
        return win;
    }
}
