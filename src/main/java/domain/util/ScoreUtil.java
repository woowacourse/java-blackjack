package domain.util;

import domain.card.Card;
import java.util.List;
import java.util.stream.IntStream;

public class ScoreUtil {
    private static final int MAXIMUM_FOR_BLACKJACK = 21;
    private static final int MULTIPLIER_FOR_MAX_CASE = 11;
    private static final int INTERVAL_FOR_SUM_CASES = 10;
    private static final int MINIMUM_SUM = 0;

    private ScoreUtil() {
    }

    public static int getScore(List<Card> cards) {
        if (containsAce(cards)) {
            return getOptimizedScore(cards);
        }

        return cards.stream()
                .mapToInt(ScoreUtil::getScoreByCard)
                .sum();
    }

    private static boolean containsAce(List<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private static int getOptimizedScore(List<Card> cards) {
        int preSum = cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(ScoreUtil::getScoreByCard)
                .sum();

        int aceCount = (int) cards.stream()
                .filter(Card::isAce)
                .count();

        return calculateOptimizeScore(preSum, aceCount);
    }

    private static int calculateOptimizeScore(int preSum, int aceCount) {
        int targetScore = MAXIMUM_FOR_BLACKJACK - preSum;

        return IntStream.iterate(
                        aceCount * MULTIPLIER_FOR_MAX_CASE,
                        i -> i > MINIMUM_SUM,
                        i -> i - INTERVAL_FOR_SUM_CASES
                )
                .filter(i -> i <= targetScore)
                .max()
                .orElse(aceCount) + preSum;
    }

    private static int getScoreByCard(Card card) {
        return card.getScore();
    }
}
