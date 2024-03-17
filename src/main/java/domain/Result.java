package domain;

import domain.participant.BetAmount;
import domain.participant.Hands;
import java.util.Arrays;
import java.util.function.BiPredicate;
import java.util.function.Function;

public enum Result {

    WIN(Result::winningCondition, BetAmount::keep),
    WIN_BLACKJACK(Result::winningBlackJackCondition, BetAmount::multiply),
    TIE(Result::tieCondition, BetAmount::zero),
    LOSE(Result::loseCondition, BetAmount::lose);

    private final BiPredicate<Hands, Hands> condition;
    private final Function<BetAmount, Profit> profitFunction;

    Result(BiPredicate<Hands, Hands> condition, Function<BetAmount, Profit> profitFunction) {
        this.condition = condition;
        this.profitFunction = profitFunction;
    }

    public static Result calculateProfit(final Hands hands, final Hands target) {
        return Arrays.stream(Result.values())
                .filter(result -> result.condition.test(hands, target))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 처리할 수 없는 값입니다."));
    }

    public static Profit calculateProfit(final Hands hands, final Hands target, final BetAmount betAmount) {
        return Arrays.stream(Result.values())
                .filter(result -> result.condition.test(hands, target))
                .map(result -> result.profitFunction.apply(betAmount))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 처리할 수 없는 값입니다."));
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
