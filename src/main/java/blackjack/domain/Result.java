package blackjack.domain;

import blackjack.domain.card.GamePoint;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Result {
    WIN((userValue, dealerValue) -> userValue.compareTo(dealerValue) > 0),
    LOSE((userValue, dealerValue) -> userValue.compareTo(dealerValue) < 0),
    DRAW((userValue, dealerValue) -> userValue.compareTo(dealerValue) == 0);

    private final BiFunction<GamePoint, GamePoint, Boolean> resultLogic;

    Result(BiFunction<GamePoint, GamePoint, Boolean> predicate) {
        this.resultLogic = predicate;
    }

    public static Result of(GamePoint userPoint, GamePoint dealerPoint) {
        return Arrays.stream(values())
                .filter(result -> result.resultLogic.apply(userPoint, dealerPoint))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
