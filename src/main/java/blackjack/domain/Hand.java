package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Hand {

    private static final int BLACKJACK_CARD_SIZE = 2;
    private static final int BLACKJACK_SYMBOL_SCORE = 21;
    private static final int ACE_UPPER_SCORE = 11;
    private static final int ACE_LOWER_SCORE = 1;
    private static final int ACE_SCORE_DIFFERENCE = ACE_UPPER_SCORE - ACE_LOWER_SCORE;

    private List<Card> cards = new ArrayList<>();

    public void add(Card... cards) {
        this.cards.addAll(List.of(cards));
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        if (hasAce()) {
            return getSumIncludingOnlyAce();
        }

        return getSumExcludingAce();
    }

    private int getSumIncludingOnlyAce() {
        int countOfAce = findCountOfAce();
        int sumOfNoAce = getSumExcludingAce() + (countOfAce * ACE_UPPER_SCORE);

        List<Integer> sums = new ArrayList<>();
        for (int aceCount = countOfAce - 1; aceCount >= 0; aceCount--) {
            sums.add(sumOfNoAce - (aceCount * ACE_SCORE_DIFFERENCE));
        }

        return findLargestSum(sums, summation -> summation <= BLACKJACK_SYMBOL_SCORE);
    }

    private int findLargestSum(List<Integer> sums, Predicate<Integer> condition) {
        return sums.stream()
                .filter(condition)
                .mapToInt(value -> value)
                .max()
                .getAsInt();
    }

    private int getSumExcludingAce() {
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
        return getScore() > BLACKJACK_SYMBOL_SCORE;
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_SIZE && getScore() == BLACKJACK_SYMBOL_SCORE;
    }
}
