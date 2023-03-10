package blackjack.domain.game;

import blackjack.domain.card.GamePoint;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Result {
    BLACK_JACK_WIN(1.5, (userValue, dealerValue) -> userValue.isBlackJack() && userValue.compareTo(dealerValue) > 0),
    WIN(1, (userValue, dealerValue) -> !userValue.isBlackJack() && userValue.compareTo(dealerValue) > 0),
    LOSE(-1, (userValue, dealerValue) -> userValue.isBusted() || userValue.compareTo(dealerValue) < 0),
    DRAW(0, (userValue, dealerValue) -> !userValue.isBusted() && userValue.compareTo(dealerValue) == 0);

    private final double prizeMultiplier;
    private final BiFunction<GamePoint, GamePoint, Boolean> resultLogic;

    Result(double prizeMultiplier, BiFunction<GamePoint, GamePoint, Boolean> predicate) {
        this.prizeMultiplier = prizeMultiplier;
        this.resultLogic = predicate;
    }

    public static Result of(GamePoint userPoint, GamePoint dealerPoint) {
        return Arrays.stream(values())
                .filter(result -> result.resultLogic.apply(userPoint, dealerPoint))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public double getPrizeMultiplier() {
        return prizeMultiplier;
    }
}
