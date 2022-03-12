package domain;

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

    public GameResult calculateFinalResult(final Cards other) {
        return GameResult.of(this, other);
    }

    public CardState getCardState() {
        return CardState.of(this);
    }

    public int getCardCount() {
        return cards.size();
    }

    public int getCardStatePower() {
        return getCardState().getStatePower();
    }

    public boolean isStand() {
        return getCardState().isStand();
    }

    public boolean isBust() {
        return getCardState().isBust();
    }

    public boolean isBlackJack() {
        return getCardState().isBlackJack();
    }
}
