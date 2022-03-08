package domain.card;

import java.util.List;

public class Cards {
    private static final int ACE_ADDITIONAL_VALUE = 10;
    private static final int BURST_COUNT = 21;

    private final List<Card> value;

    public Cards(List<Card> cards) {
        this.value = cards;
    }

    public int sum() {
        int sum = value.stream()
                .mapToInt(Card::toInt)
                .sum();

        if (hasAce() && !exceedBurst(sum)) {
            sum += ACE_ADDITIONAL_VALUE;
        }

        return sum;
    }

    private boolean exceedBurst(int sum) {
        return sum + ACE_ADDITIONAL_VALUE > BURST_COUNT;
    }

    private boolean hasAce() {
        return value.stream()
                .anyMatch(Card::isAce);
    }
}
