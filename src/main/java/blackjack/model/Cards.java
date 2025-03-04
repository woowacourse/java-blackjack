package blackjack.model;

import java.util.List;

public class Cards {

    private final List<Card> values;

    public Cards(final List<Card> values) {
        this.values = values;
    }

    public int sumAll() {
        return values.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

}
