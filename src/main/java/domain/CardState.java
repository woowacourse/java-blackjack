package domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum CardState {
    BLACKJACK(CardState::isBlackJack, 3),
    STAND(cards -> !(isBlackJack(cards)) && cards.calculateSum() <= 21, 2),
    BUST(cards -> cards.calculateSum() > 21, 1);

    private static final int BLACK_JACK_CARD_SCORE = 21;
    private static final int BLACK_JACK_CARD_COUNT = 2;
    private static final String CANNOT_FIND_CARD_STATE_MESSAGE = "카드 상태를 구할 수 없습니다.";

    private final Predicate<Cards> predicate;
    private final int statePower;

    CardState(final Predicate<Cards> predicate, final int statePower) {
        this.predicate = predicate;
        this.statePower = statePower;
    }

    public static CardState from(final Cards cards) {
        return Arrays
                .stream(CardState.values())
                .filter(cardState -> cardState.predicate.test(cards))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(CANNOT_FIND_CARD_STATE_MESSAGE));
    }

    private static boolean isBlackJack(final Cards cards) {
        return cards.getCardsCount() == BLACK_JACK_CARD_COUNT && cards.calculateSum() == BLACK_JACK_CARD_SCORE;
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
