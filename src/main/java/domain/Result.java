package domain;

import domain.participant.Hands;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

public enum Result {

    BLACK_JACK_WIN(Result::blackJackWinningCondition, BetAmount::blackJackWinAmount),
    WIN(Result::winningCondition, BetAmount::winAmount),
    TIE(Result::tieCondition, BetAmount::tieAmount),
    LOSE(Result::loseCondition, BetAmount::loseAmount);

    private final BiPredicate<Hands, Hands> condition;
    private final Function<BetAmount, Amount> calculate;

    Result(final BiPredicate<Hands, Hands> condition, final Function<BetAmount, Amount> calculate) {
        this.condition = condition;
        this.calculate = calculate;
    }

    public Result reverse() {
        if (Result.WIN.equals(this) || Result.BLACK_JACK_WIN.equals(this)) {
            return LOSE;
        }
        if (Result.LOSE.equals(this)) {
            return WIN;
        }
        return TIE;
    }

    public static Result calculateOf(final Hands hands, final Hands target) {
        return Arrays.stream(Result.values())
                .filter(result -> result.condition.test(hands, target))
                .findFirst()
                .orElseThrow();
    }

    public Amount apply(BetAmount betAmount) {
        return calculate.apply(betAmount);
    }

    private static boolean blackJackWinningCondition(Hands hands, Hands target) {
        return hands.isBlackJack() && !target.isBlackJack();
    }

    private static boolean winningCondition(final Hands hands, final Hands target) {
        return (!hands.isBust() && target.isBust()) || (hands.sum() > target.sum() && !hands.isBust());
    }

    private static boolean tieCondition(final Hands hands, final Hands target) {
        return hands.sum() == target.sum() && !hands.isBust();
    }

    private static boolean loseCondition(final Hands hands, final Hands target) {
        return hands.isBust() || hands.sum() < target.sum() || !target.isBust();
    }
}
