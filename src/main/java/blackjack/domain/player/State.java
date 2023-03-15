package blackjack.domain.player;

import java.util.Arrays;
import java.util.function.Function;

public enum State {
    BLACKJACK(Cards::isBlackjack),
    STAY(Cards::isBlackjackScore),
    BUST(Cards::isBust),
    HIT(cards -> false);

    private final Function<Cards, Boolean> expression;

    State(final Function<Cards, Boolean> expression) {
        this.expression = expression;
    }

    public static State calculateState(final Cards cards) {
        return Arrays.stream(values())
                .filter(state -> state.expression.apply(cards))
                .findFirst()
                .orElse(HIT);
    }

    public boolean isBlackjack() {
        return this == BLACKJACK;
    }

    public boolean isPlayable() {
        return this == HIT;
    }

    public boolean isBust() {
        return this == BUST;
    }

    public boolean isNotBust() {
        return this != BUST;
    }
}
