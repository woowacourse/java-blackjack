package blackjack.domain;

import blackjack.domain.card.GamePoint;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Result {
    WIN(1, (userValue, dealerValue) -> userValue.compareTo(dealerValue) > 0),
    LOSE(-1, (userValue, dealerValue) -> userValue.isBusted() || userValue.compareTo(dealerValue) < 0),
    DRAW(0, (userValue, dealerValue) -> !userValue.isBusted() && userValue.compareTo(dealerValue) == 0);

    private final int prizeMultiplier;
    private final BiFunction<GamePoint, GamePoint, Boolean> resultLogic;

    Result(int prizeMultiplier, BiFunction<GamePoint, GamePoint, Boolean> predicate) {
        this.prizeMultiplier = prizeMultiplier;
        this.resultLogic = predicate;
    }

    public static Result of(GamePoint userPoint, GamePoint dealerPoint) {
        return Arrays.stream(values())
                .filter(result -> result.resultLogic.apply(userPoint, dealerPoint))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public int getPrizeMultiplier() {
        return prizeMultiplier;
    }
}
