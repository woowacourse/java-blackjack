package blackjack.domain.card;

import java.util.function.Predicate;

public enum CardHandState {

    CAN_HIT, STAY, BLACKJACK, BUST;

    public static CardHandState ofPlayer(final CardBundle cards) {
        return of(cards, CardBundle::isBlackjackScore);
    }

    public static CardHandState ofDealer(final CardBundle cards) {
        return of(cards, CardBundle::isDealerFinished);
    }

    private static CardHandState of(final CardBundle cards,
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

    public boolean isStay() {
        return this == STAY;
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }

    public boolean isBust() {
        return this == BUST;
    }
}
