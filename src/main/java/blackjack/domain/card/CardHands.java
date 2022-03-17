package blackjack.domain.card;

import static blackjack.domain.BlackjackRule.BLACKJACK_SCORE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CardHands {

    private static final int DEFAULT_SCORE = 0;

    private final List<Card> cards;

    public CardHands() {
        this.cards = new ArrayList<>();
    }

    public CardHands(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        List<Integer> sortedTotalSums = calculateSortedTotalSums();
        if (sortedTotalSums.isEmpty()) {
            return DEFAULT_SCORE;
        }

        final int lowestTotalSum = sortedTotalSums.get(0);
        if (BLACKJACK_SCORE.isNotOverThan(lowestTotalSum)) {
            return lowestTotalSum;
        }

        return getHighestTotalSumsUnderBlackjack(sortedTotalSums);
    }

    private List<Integer> calculateSortedTotalSums() {
        final Set<Integer> totalSumSets = calculateTotalSums(Set.of(0), 0);
        final List<Integer> totalSums = new ArrayList<>(totalSumSets);
        Collections.sort(totalSums);
        return totalSums;
    }

    private Set<Integer> calculateTotalSums(final Set<Integer> numbers, final int depth) {
        if (depth < 0 || cards.size() < depth) {
            throw new IndexOutOfBoundsException("카드 인덱스의 범위를 벗어났습니다.");
        }
        if (depth == cards.size()) {
            return numbers;
        }
        final Set<Integer> partialTotalSums = calculatePartialTotalSum(numbers, cards.get(depth));
        return calculateTotalSums(partialTotalSums, depth + 1);
    }

    private Set<Integer> calculatePartialTotalSum(final Set<Integer> numbers, final Card card) {
        return numbers.stream()
                .map(card::addNumbers)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    private int getHighestTotalSumsUnderBlackjack(final List<Integer> sortedTotalSums) {
        final List<Integer> revereSortedTotalSums = new ArrayList<>(sortedTotalSums);
        Collections.reverse(revereSortedTotalSums);
        return revereSortedTotalSums.stream()
                .filter(score -> BLACKJACK_SCORE.isNotUnderThan(score))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("21 이하의 점수가 존재하지 않습니다."));
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

}
