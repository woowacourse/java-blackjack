package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> value;

    public Cards() {
        this.value = new ArrayList<>();
    }

    public Cards(List<Card> cards) {
        this.value = new ArrayList<>(cards);
    }

    public void add(Card card) {
        this.value.add(card);
    }

    public int getCount() {
        return value.size();
    }

    public int sum() {
        return value.stream()
                .mapToInt(Card::toInt)
                .sum();
    }

    public boolean hasAce() {
        return value.stream()
                .anyMatch(Card::isAce);
    }

    public List<Card> getValue() {
        return Collections.unmodifiableList(value);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "value=" + value +
                '}';
    }
}
