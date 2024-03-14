package blackjack.domain.card;


import java.util.ArrayList;
import java.util.List;

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

    public void add(final Card card) {
        value.add(card);
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
}
