package blackjack.domain.status;

import blackjack.domain.card.Card;

import java.util.Arrays;

public class StateFactory {
    public static State draw(Card... cards) {
        int result = calculate(cards);
        if (result == 21)
            return new Blackjack(cards);
        if (result < 21)
            return new Hit(cards);
        return new Bust();
    }

    private static int calculate(Card[] cards) {
        int sum = getSum(cards);
        double aceCount = countAce(cards);
        for (int count = 0; count < aceCount; count++) {
            if (sum > 21) {
                sum -= 10;
            }
        }
        return sum;
    }

    private static int getSum(Card[] cards) {
        return Arrays.stream(cards)
                     .mapToInt(Card::getValue)
                     .sum();
    }

    private static long countAce(Card[] cards) {
        return Arrays.stream(cards)
                     .filter(Card::isAce)
                     .count();
    }
}
