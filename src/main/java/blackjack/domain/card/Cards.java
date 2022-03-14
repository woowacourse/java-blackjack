package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static final int ACE_ADDITIONAL_VALUE = 10;
    private static final int BLACKJACK_VALUE = 21;

    private final List<Card> value;

    public Cards(List<Card> cards) {
        this.value = new ArrayList<>(cards);
    }

    public void add(Card card) {
        this.value.add(card);
    }

    public boolean isBust() {
        return sum() > BLACKJACK_VALUE;
    }

    public boolean isBlackjack() {
        return sum() == BLACKJACK_VALUE;
    }

    public int sum() {
        int sum = value.stream()
                .mapToInt(Card::toInt)
                .sum();

        if (canAddAddtionalValue(sum)) {
            sum += ACE_ADDITIONAL_VALUE;
        }

        return sum;
    }

    private boolean canAddAddtionalValue(int sum) {
        return hasAce() && !exceedBust(sum);
    }

    private boolean hasAce() {
        return value.stream()
                .anyMatch(Card::isAce);
    }

    private boolean exceedBust(int sum) {
        return sum + ACE_ADDITIONAL_VALUE > BLACKJACK_VALUE;
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
