package domain.gamer;

import domain.deck.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class Hand {

    public static final int BUST_NUMBER = 21;
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public int calculateSimpleSumOfRank() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public int calculateSumOfRank() {
        if (this.hasAce()) {
            final List<Integer> sumOfRanks = calculateAllSums(cards);

            return sumOfRanks.stream()
                    .filter(sum -> sum <= Hand.BUST_NUMBER)
                    .max(Integer::compareTo)
                    .orElseGet(getMinSum(sumOfRanks));
        }
        return this.calculateSimpleSumOfRank();
    }

    private static Supplier<Integer> getMinSum(final List<Integer> sumOfRanks) {
        return () -> sumOfRanks.stream().min(Integer::compareTo).orElse(0);
    }

    private List<Integer> calculateAllSums(final List<Card> cards) {
        final Set<Integer> resultSet = new HashSet<>();
        calculateAllSums(cards, 0, 0, resultSet);
        final List<Integer> resultList = new ArrayList<>(resultSet);
        Collections.sort(resultList);
        return resultList;
    }

    private void calculateAllSums(final List<Card> cards,
                                  final int index,
                                  final int currentSum,
                                  final Set<Integer> resultSet
    ) {
        if (index == cards.size()) {
            resultSet.add(currentSum);
            return;
        }
        final Card card = cards.get(index);
        if (card.isAce()) {
            calculateAllSums(cards, index + 1, currentSum + 1, resultSet);
            calculateAllSums(cards, index + 1, currentSum + 11, resultSet);
            return;
        }
        calculateAllSums(cards, index + 1, currentSum + card.getScore(), resultSet);
    }

    public boolean isBust() {
        return calculateSumOfRank() > BUST_NUMBER;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && calculateSumOfRank() == 21;
    }

    public boolean isBustThreshold() {
        return calculateSumOfRank() == BUST_NUMBER;
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return cards;
    }
}
