package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import java.util.List;
import java.util.stream.IntStream;

public class Score {

    private static final int BUST_STANDARD = 21;

    private final int value;

    private Score(int value) {
        this.value = value;
    }

    public static Score from(List<Card> cards) {
        return new Score(calculateScore(List.copyOf(cards)));
    }

    private static int calculateScore(List<Card> cards) {
        int minimumScore = calculateMinimumScore(cards);
        if (containsAce(cards)) {
            return IntStream.rangeClosed(0, 1)
                    .map(i -> minimumScore + (1 - i) * 10)
                    .filter(i -> i <= BUST_STANDARD)
                    .findFirst()
                    .orElse(minimumScore);
        }
        return minimumScore;
    }

    private static int calculateMinimumScore(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    private static boolean containsAce(List<Card> cards) {
        return cards.stream()
                .anyMatch(card -> card.isSameValueWith(Denomination.ACE));
    }

    public int getValue() {
        return value;
    }
}
