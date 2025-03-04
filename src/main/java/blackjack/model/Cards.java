package blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> values;

    public Cards(final List<Card> values) {
        this.values = new ArrayList<>(values);
    }

    public int sumAll() {
        return values.stream()
                .mapToInt(Card::getCardNumber)
                .sum();
    }

    public boolean hasSize(final int size) {
        return values.size() == size;
    }

    public void merge(final Cards otherCards) {
        this.values.addAll(otherCards.values);
    }

    public List<Card> getValues() {
        return values;
    }

}
