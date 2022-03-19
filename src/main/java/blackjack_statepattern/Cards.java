package blackjack_statepattern;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private final List<Card> value;

    public Cards(List<Card> cards) {
        this.value = cards;
    }

    public Cards() {
        this(new ArrayList<>());
    }

    public int sum() {
        return value.stream()
                .mapToInt(Card::score)
                .sum();
    }

    public boolean hasAce() {
        return value.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        return 11 == sum() && hasAce();
    }

    public Cards add(Card card) {
        final var newValue = new ArrayList<>(value);
        newValue.add(card);

        return new Cards(newValue);
    }

    public boolean isBust() {
        return sum() > 21;
    }

    public boolean isReady() {
        return value.size() < 2;
    }
}
