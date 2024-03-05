package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> values;

    public Cards(final List<Card> cards) {
        this.values = new ArrayList<>(cards);
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

    public void add(final Card card) {
        values.add(card);
    }
}