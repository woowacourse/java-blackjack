package blackjack.domain;

import java.util.List;

public class Cards {

    private final List<Card> values;

    public Cards(final List<Card> cards) {
        this.values = cards;
    }

    public int calculate() {
        int sum = sum();
        int aceCount = countAce();

        while (sum > 21 && aceCount > 0) {
            sum = sum - 10;
            aceCount--;
        }
        return sum;
    }

    private int countAce() {
        return (int) values.stream()
                .filter(Card::isAce)
                .count();
    }

    private int sum() {
        return values.stream()
                .mapToInt(Card::getRealNumber)
                .sum();
    }
}