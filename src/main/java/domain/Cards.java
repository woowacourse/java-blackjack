package domain;

import domain.card.CanAddCardThreshold;
import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public final class Cards {
    private final List<Card> cards = new ArrayList<>();
    private final CanAddCardThreshold threshold;

    public Cards(final CanAddCardThreshold threshold) {
        this.threshold = threshold;
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean canAddCard() {
        return threshold.canAdd(calculateSum());
    }

    public int calculateSum() {
        int minimumSum = cards.stream().mapToInt(Card::getScore).sum();
        int maximumSum = calculateMaximumSum(minimumSum);
        if (maximumSum > 21) {
            return minimumSum;
        }
        return maximumSum;
    }

    private int calculateMaximumSum(final int minimumSum) {
        if (hasAce()) {
            return minimumSum + 10;
        }
        return minimumSum;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAceCard);
    }
}
