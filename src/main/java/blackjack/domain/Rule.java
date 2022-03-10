package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import java.util.List;
import java.util.stream.IntStream;

public enum Rule {

    INSTANCE;

    private static final int BLACKJACK_COUNT = 2;
    private static final int BLACKJACK_SCORE = 21;

    public int calculateSum(List<Card> cards) {
        int aceCount = calculateAceCount(cards);
        int sum = calculateMinimumSum(cards);
        return IntStream.rangeClosed(0, aceCount)
                .map(i -> sum + (aceCount - i) * 10)
                .filter(i -> i <= BLACKJACK_SCORE)
                .findFirst()
                .orElse(sum);
    }

    private int calculateMinimumSum(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    private int calculateAceCount(List<Card> cards) {
        return (int) cards.stream()
                .filter(card -> card.isSameValueWith(Denomination.ACE))
                .count();
    }

    public boolean isBust(List<Card> cards) {
        return calculateSum(cards) > BLACKJACK_SCORE;
    }

    public boolean isBlackJack(List<Card> cards) {
        return cards.size() == BLACKJACK_COUNT && calculateSum(cards) == BLACKJACK_SCORE;
    }
}
