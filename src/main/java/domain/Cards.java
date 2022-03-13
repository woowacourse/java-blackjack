package domain;

import domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public final class Cards {
    private static final int MAXIMUM_SUM_VALUE = 21;
    private static final int DIFFERENCE_MAX_MIN_SUM_VALUE = 10;

    private final List<Card> cards = new ArrayList<>();
    private final HitThreshold threshold;

    public Cards(final HitThreshold threshold) {
        this.threshold = threshold;
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean canAddCard() {
        return threshold.canAdd(calculateSum());
    }

    public int calculateSum() {
        final int minimumSum = cards.stream().mapToInt(Card::getScore).sum();
        final int maximumSum = calculateMaximumSum(minimumSum);
        if (maximumSum > MAXIMUM_SUM_VALUE) {
            return minimumSum;
        }
        return maximumSum;
    }

    private int calculateMaximumSum(final int minimumSum) {
        if (hasAce()) {
            return minimumSum + DIFFERENCE_MAX_MIN_SUM_VALUE;
        }
        return minimumSum;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAceCard);
    }

    public GameResult calculateGameResult(final Cards other) {
        return GameResult.of(this, other);
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

    public CardState getCardState() {
        return CardState.from(this);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public int getCardStatePower() {
        return getCardState().getStatePower();
    }

    public int getCardsCount() {
        return cards.size();
    }
}
