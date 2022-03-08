package blackjack.domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class Cards {
    private final Set<Card> value;

    public Cards(Set<Card> value) {
        this.value = new LinkedHashSet<>(value);
    }

    public int getSize() {
        return value.size();
    }

    public int sum() {
        return value.stream()
                .mapToInt(Card::getNumberValue)
                .sum();
    }

    public void add(Card card) {
        value.add(card);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "value=" + value +
                '}';
    }
}
