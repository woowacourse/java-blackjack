package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Hand {

    private List<Card> cards = new ArrayList<>();

    public void add(Card... cards) {
        this.cards.addAll(List.of(cards));
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        int max = 0;
        
        if (hasAce()) {
            max = getAceSum();
            return max;
        }

        return sumOfNoAce();
    }

    private int getAceSum() {
        int countOfAce = findCountOfAce();
        int sumOfNoAce = sumOfNoAce() + (countOfAce * 11);
        
        List<Integer> sums = new ArrayList<>();
        for (int aceCount = countOfAce - 1; aceCount >= 0; aceCount--) {
            sums.add(sumOfNoAce - (aceCount * 10));
        }

        return findLargestSum(sums, summation -> summation <= 21);
    }

    private int findLargestSum(List<Integer> sums, Predicate<Integer> condition) {
        return sums.stream()
                .filter(condition)
                .mapToInt(value -> value)
                .max()
                .getAsInt();
    }

    private int sumOfNoAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getScore)
                .sum();
    }

    private boolean hasAce() {
        return findCountOfAce() > 0;
    }

    private int findCountOfAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public boolean isBust() {
        return getScore() > 21;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getScore() == 21;
    }
}
