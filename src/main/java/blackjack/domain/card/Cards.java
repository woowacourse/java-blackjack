package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Cards {

    private static final int BLACKJACK = 21;
    private static final int DEFAULT_SCORE = 0;

    private final List<Card> cards = new ArrayList<>();

    public void addCard(final Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        List<Integer> sortedTotalSums = calculateSortedTotalSums();
        if (sortedTotalSums.isEmpty()) {
            return DEFAULT_SCORE;
        }

        final int lowestTotalSum = sortedTotalSums.get(0);
        if (lowestTotalSum >= BLACKJACK) {
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
                .filter(number -> number <= BLACKJACK)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("21 이하의 점수가 존재하지 않습니다."));
    }

    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK;
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK;
    }

    public List<String> getCardNames() {
        return cards.stream()
                .map(Card::getCardName)
                .collect(Collectors.toUnmodifiableList());
    }

    public String getFirstCardName() {
        validateCardNotEmpty();
        final Card firstCard = cards.get(0);
        return firstCard.getCardName();
    }

    private void validateCardNotEmpty() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("카드가 존재하지 않습니다.");
        }
    }

}
