package blackjack.domain.card;

import java.util.function.Predicate;

public enum CardHandState {

    CAN_HIT, STAY, BLACKJACK, BUST;

    public static CardHandState of(final CardBundle cards,
                                   final Predicate<CardBundle> stayStrategy) {
        if (cards.isBust()) {
            return BUST;
        }
        if (cards.isBlackjack()) {
            return BLACKJACK;
        }
        if (stayStrategy.test(cards)) {
            return STAY;
        }
        return CAN_HIT;
    }

    public boolean isFinished() {
        return this != CAN_HIT;
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }

    public boolean isBust() {
        return this == BUST;
    }
}
