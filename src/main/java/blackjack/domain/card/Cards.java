package blackjack.domain.card;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {
    private static final int BUST_SIZE = 21;
    private static final int CHANGE_A_VALUE = 10;
    private final List<Card> value;

    public Cards(final List<Card> cards) {
        this.value = new ArrayList<>(cards);
    }

    public int calculate() {
        if (containAce() && isNotBust()) {
            return sum() + CHANGE_A_VALUE;
        }
        return sum();
    }

    private boolean containAce() {
        return value.stream()
                    .anyMatch(Card::isAce);
    }

    private boolean isNotBust() {
        return sum() + CHANGE_A_VALUE <= BUST_SIZE;
    }

    public boolean isBust() {
        return calculate() > BUST_SIZE;
    }

    public Cards draw(final Card card) {
        this.value.add(card);
        return new Cards(this.value);
    }

    private int sum() {
        return value.stream()
                    .mapToInt(Card::getCardScore)
                    .sum();
    }

    public Card getFirstCard() {
        return value.get(0);
    }

    public List<Card> toList() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final Cards cards)) return false;
        return Objects.equals(this.value, cards.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value);
    }
}
