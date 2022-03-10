package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import java.util.List;
import java.util.stream.IntStream;

public enum Rule {

    INSTANCE;

    public static final int BLACKJACK = 21;

    public int calculateSum(List<Card> cards) {
        int aceCount = calculateAceCount(cards);
        int sum = calculateMinimumSum(cards);
        return IntStream.rangeClosed(0, aceCount)
                .map(i -> sum + (aceCount - i) * 10)
                .filter(i -> i <= BLACKJACK)
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
        return calculateSum(cards) > BLACKJACK;
    }

    public boolean isBlackJack(List<Card> cards) {
        return calculateSum(cards) == BLACKJACK;
    }
}
