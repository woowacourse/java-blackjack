package blackjack.domain.card;

import static blackjack.domain.BlackjackScoreRule.ENABLE_MAXIMUM_SCORE_UNDER_BUST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ScoreCalculator {

    private static final int DEFAULT_SCORE = 0;

    public static int calculateScore(final List<Card> cards) {
        final List<Card> copyCards = new ArrayList<>(cards);
        List<Integer> sortedTotalSums = calculateSortedTotalSums(copyCards);
        if (sortedTotalSums.isEmpty()) {
            return DEFAULT_SCORE;
        }

        final int lowestTotalSum = sortedTotalSums.get(0);
        if (ENABLE_MAXIMUM_SCORE_UNDER_BUST.isNotOverThan(lowestTotalSum)) {
            return lowestTotalSum;
        }
        return getHighestTotalSumsUnderBlackjack(sortedTotalSums);
    }

    private static List<Integer> calculateSortedTotalSums(final List<Card> cards) {
        final Set<Integer> totalSumSets = calculateTotalSums(cards, Set.of(0), 0);
        final List<Integer> totalSums = new ArrayList<>(totalSumSets);
        Collections.sort(totalSums);
        return totalSums;
    }

    private static Set<Integer> calculateTotalSums(final List<Card> cards,
                                                   final Set<Integer> numbers,
                                                   final int depth) {
        if ((depth < 0) || (cards.size() < depth)) {
            throw new IndexOutOfBoundsException("카드 인덱스의 범위를 벗어났습니다.");
        }
        if (depth == cards.size()) {
            return numbers;
        }
        final Set<Integer> partialTotalSums = calculatePartialTotalSum(numbers, cards.get(depth));
        return calculateTotalSums(cards, partialTotalSums, depth + 1);
    }

    private static Set<Integer> calculatePartialTotalSum(final Set<Integer> numbers, final Card card) {
        return numbers.stream()
                .map(card::addNumbers)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    private static int getHighestTotalSumsUnderBlackjack(final List<Integer> sortedTotalSums) {
        final List<Integer> revereSortedTotalSums = new ArrayList<>(sortedTotalSums);
        Collections.reverse(revereSortedTotalSums);
        return revereSortedTotalSums.stream()
                .filter(ENABLE_MAXIMUM_SCORE_UNDER_BUST::isNotUnderThan)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("21 이하의 점수가 존재하지 않습니다."));
    }

}
