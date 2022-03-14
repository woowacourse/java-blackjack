package blackjack.domain.card;

public enum CardHandState {

    CAN_HIT, STAY, MAX_SCORE, BLACKJACK, BUST;

    public static CardHandState of(CardBundle cards) {
        if (cards.isBust()) {
            return BUST;
        }
        if (cards.isBlackjack()) {
            return BLACKJACK;
        }
        if (cards.isBlackjackScore()) {
            return MAX_SCORE;
        }
        return CAN_HIT;
    }

    public boolean isFinished() {
        return this != CAN_HIT;
    }

    public boolean isMaxScore() {
        return this == MAX_SCORE;
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }

    public boolean isBust() {
        return this == BUST;
    }
}
