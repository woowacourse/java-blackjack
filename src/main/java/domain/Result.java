package domain;

import domain.participant.BetAmount;
import domain.participant.Hands;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

public enum Result {

    WIN(Result::winningCondition, betAmount -> betAmount),
    WIN_BLACKJACK(Result::winningBlackJackCondition, betAmount -> betAmount.multiply(1.5)),
    TIE(Result::tieCondition, BetAmount::makeZero),
    LOSE(Result::loseCondition, BetAmount::lose);

    private final BiPredicate<Hands, Hands> condition;
    private final Function<BetAmount, BetAmount> betAmountFunction;

    Result(BiPredicate<Hands, Hands> condition, Function<BetAmount, BetAmount> betAmountFunction) {
        this.condition = condition;
        this.betAmountFunction = betAmountFunction;
    }

    public Result reverse() {
        if (Result.WIN.equals(this) || Result.WIN_BLACKJACK.equals(this)) {
            return LOSE;
        }

        if (Result.LOSE.equals(this)) {
            return WIN;
        }

        return TIE;
    }

    public static Result calculate(Hands hands, Hands target) {
        return Arrays.stream(Result.values())
                .filter(result -> result.condition.test(hands, target))
                .findFirst()
                .get();
    }

    public static BetAmount calculate(final Hands hands, final Hands target, final BetAmount betAmount) {
        return Arrays.stream(Result.values())
                .filter(result -> result.condition.test(hands, target))
                .map(result -> result.betAmountFunction.apply(betAmount))
                .findFirst()
                .get();
    }

    public BetAmount calculate(final BetAmount betAmount) {
        return this.betAmountFunction.apply(betAmount);
    }

    private static boolean winningCondition(final Hands hands, final Hands target) {
        return (!hands.isBust() && target.isBust())
                || (!hands.isBust() && hands.sum() > target.sum() && !hands.isBlackJack())
                || (!hands.isBust() && hands.sum() == target.sum() && !hands.isBlackJack()
                && hands.size() < target.size());
    }

    private static boolean winningBlackJackCondition(final Hands hands, final Hands target) {
        return hands.sum() > target.sum() && hands.isBlackJack();
    }

    private static boolean tieCondition(final Hands hands, final Hands target) {
        return hands.sum() == target.sum() && hands.size() == target.size() && !hands.isBust();
    }

    private static boolean loseCondition(final Hands hands, final Hands target) {
        return hands.isBust() || hands.sum() < target.sum() || !target.isBust();
    }
}
