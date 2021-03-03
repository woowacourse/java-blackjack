package blackjack;

import java.util.ArrayList;
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
        return calculateMyCardSum() >= BUST_LIMIT;
    }

    public int calculateMyCardSum() {
        int aceCount = (int) myCards.stream()
                .filter(card -> card.isAce())
                .count();
        if (aceCount < 2) {
            return calculateMyCardSum2();
        }
        return calculateMyCardSumWithAce(aceCount);
    }

    public int calculateMyCardSum2() {
        return myCards.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    private int calculateMyCardSumWithAce(int aceCount) {
        List<Integer> possibleSum = new ArrayList<>();
        int sum = 0;
        for (Card card : myCards) {
            if (card.isAce())
                continue;
            sum += card.getCardNumber();
        }
        List<Integer> aceSums = calculateAceSum(aceCount);
        for (int aceSum : aceSums) {
            possibleSum.add(sum + aceSum);
        }
        return possibleSum.stream()
                .filter(aceSum -> aceSum < BUST_LIMIT)
                .mapToInt(Integer::intValue)
                .max()
                .orElse(BUST_LIMIT);
    }

    private List<Integer> calculateAceSum(int aceCount) {
        List<Integer> aceSums = new ArrayList<>();
        aceSums.add(10 + aceCount);
        aceSums.add(aceCount);
        return aceSums;
    }
}
