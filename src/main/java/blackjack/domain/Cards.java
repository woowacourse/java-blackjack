package blackjack.domain;

import java.util.List;

public class Cards {

    private final List<Card> values;

    public Cards(final List<Card> cards) {
        this.values = cards;
    }

    public int countAce() {
        return (int) values.stream()
                .filter(Card::isAce)
                .count();
    }

    public int sum() {
        return values.stream()
                .mapToInt(Card::getRealNumber)
                .sum();
    }
}