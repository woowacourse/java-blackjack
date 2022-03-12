package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;

import java.util.List;
import java.util.stream.IntStream;

public class Rule {

    private static final int BLACKJACK_COUNT = 2;
    private static final int BLACKJACK_SCORE = 21;

    private Rule() {
    }

    public static int calculateScore(List<Card> cards) {
        int aceCount = calculateAceCount(cards);
        int sum = calculateMinimumSum(cards);
        return calculateSum(aceCount, sum);
    }

    private static int calculateSum(int aceCount, int sum) {
        return IntStream.rangeClosed(0, aceCount)
            .map(i -> sum + (aceCount - i) * 10)
            .filter(i -> i <= BLACKJACK_SCORE)
            .findFirst()
            .orElse(sum);
    }

    private static int calculateMinimumSum(List<Card> cards) {
        return cards.stream()
            .mapToInt(Card::getValue)
            .sum();
    }

    private static int calculateAceCount(List<Card> cards) {
        return (int)cards.stream()
            .filter(card -> card.isSameValueWith(Denomination.ACE))
            .count();
    }

    public static boolean isBust(List<Card> cards) {
        return calculateScore(cards) > BLACKJACK_SCORE;
    }

    public static boolean isBlackJack(List<Card> cards) {
        return cards.size() == BLACKJACK_COUNT && calculateScore(cards) == BLACKJACK_SCORE;
    }
}
