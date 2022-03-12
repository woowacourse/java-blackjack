package domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum CardState {
    BLACKJACK(CardState::isBlackJack, 3),
    STAND(cards -> !(isBlackJack(cards)) && cards.calculateSum() <= 21, 2),
    BUST(cards -> cards.calculateSum() > 21, 1);

    private static final int BLACK_JACK_SCORE = 21;
    private static final int BLACK_JACK_CARD_COUNT = 2;

    private final Predicate<Cards> predicate;
    private final int statePower;

    CardState(final Predicate<Cards> predicate, final int statePower) {
        this.predicate = predicate;
        this.statePower = statePower;
    }

    public static CardState of(final Cards cards) {
        return Arrays
                .stream(CardState.values())
                .filter(cardState -> cardState.predicate.test(cards))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static boolean isBlackJack(final Cards cards) {
        return cards.getCardCount() == BLACK_JACK_CARD_COUNT && cards.calculateSum() == BLACK_JACK_SCORE;
    }

    public boolean isStand() {
        return this == STAND;
    }

    public boolean isBust() {
        return this == BUST;
    }

    public boolean isBlackJack() {
        return this == BLACKJACK;
    }

    public int getStatePower() {
        return statePower;
    }
}
