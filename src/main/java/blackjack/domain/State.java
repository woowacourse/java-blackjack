package blackjack.domain;

import java.util.Arrays;
import java.util.function.Function;

public enum State {
    BLACKJACK(Cards::isBlackjack),
    STOP(Cards::isBlackjackScore),
    BUST(Cards::isBust),
    PLAY(cards -> false);

    private Function<Cards, Boolean> expression;

    State(final Function<Cards, Boolean> expression) {
        this.expression = expression;
    }

    public static State calculateState(final Cards cards) {
        return Arrays.stream(values())
                .filter(state -> state.expression.apply(cards))
                .findFirst()
                .orElse(PLAY);
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }

    public boolean isPlayable() {
        return this == PLAY;
    }

    public boolean isBust() {
        return this == BUST;
    }

    public boolean isNotBust() {
        return this != BUST;
    }
}
