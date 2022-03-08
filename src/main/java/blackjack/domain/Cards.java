package blackjack.domain;

import java.util.LinkedHashSet;
import java.util.Set;

public class Cards {
    private final Set<Card> value;

    public Cards(Set<Card> value) {
        this.value = new LinkedHashSet<>(value);
    }

    public void add(Card card) {
        value.add(card);
    }

    public int getSize() {
        return value.size();
    }

    public Status isBust() {
        if (sum() > 21) {
            return Status.BUST;
        }

        return Status.NOT_BUST;
    }

    public int sum() {
        int sum = 0;
        int countAce = 0;
        for (Card card : value) {
            int numberValue = card.getNumberValue();
            sum += numberValue;
            if (card.isAce()) {
                countAce++;
            }
        }
        if (countAce > 0) {
            sum = getSum(sum, countAce);
        }
        return sum;
    }

    private int getSum(int sum, int countAce) {
        while (sum > 21 && countAce > 0) {
            sum -= 10;
            countAce--;
        }
        return sum;
    }

    public boolean hasAce() {
        return value.stream()
                .anyMatch(Card::isAce);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "value=" + value +
                '}';
    }
}
