package domain.card;

import domain.GameResult;
import domain.HitThreshold;
import java.util.ArrayList;
import java.util.List;

public final class Cards {
    private static final int MAXIMUM_SUM_VALUE = 21;
    private static final int DIFFERENCE_MAX_MIN_SUM_VALUE = 10;
    private static final String CAN_NOT_ADD_CARD_MESSAGE = "카드를 더 이상 받을 수 없습니다.";

    private final List<Card> cards = new ArrayList<>();
    private final HitThreshold threshold;

    public Cards(final HitThreshold threshold) {
        this.threshold = threshold;
    }

    public boolean add(final Card card, boolean... request) {
        if (request.length == 0) {
            return addWithoutRequest(card);
        }
        if (request[0]) {
            checkCanAddCard();
            return addWithoutRequest(card);
        }
        return false;
    }

    private boolean addWithoutRequest(final Card card) {
        if (canAddCard()) {
            cards.add(card);
            return true;
        }
        return false;
    }

    private void checkCanAddCard() {
        if (!canAddCard()) {
            throw new IllegalArgumentException(CAN_NOT_ADD_CARD_MESSAGE);
        }
    }

    boolean canAddCard() {
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
