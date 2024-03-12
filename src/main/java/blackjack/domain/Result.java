package blackjack.domain;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public enum Result {

    BLACKJACK_WIN((Player player, Dealer dealer)
            -> (player.isFirstTurnBackJack() && dealer.isNotBlackJack())
            , money -> 1.5 * money
    ),
    WIN((Player player, Dealer dealer)
            -> (dealer.isBurst() && player.isNotBurst())
            || player.isNotBurst() && player.hasHigherScore(dealer)
            , moeny -> moeny
    ),
    LOSE((Player player, Dealer dealer)
            -> player.isBurst()
            || player.isNotBurst() && player.hasLowerScore(dealer)
            , money -> -1 * money
    ),
    DRAW((Player player, Dealer dealer)
            -> (dealer.isNotBurst() && player.isNotBurst())
            && player.hasSameScore(dealer)
            , money -> Result.nonProfit()
    );

    private static final Double NON_PROFIT = 0.0;
    private final BiPredicate<Player, Dealer> resultCondition;
    private final UnaryOperator<Double> calculateProfit;

    Result(BiPredicate<Player, Dealer> resultCondition
            , UnaryOperator<Double> calculateProfit) {
        this.resultCondition = resultCondition;
        this.calculateProfit = calculateProfit;
    }

    public static double calculateProfit(Player player, Dealer dealer) {
        Optional<Result> targetResult = Stream.of(values())
                .filter(result -> result.resultCondition.test(player, dealer))
                .findAny();

        if (targetResult.isPresent()) {
            return calculateBattingFromResult(targetResult.get(), player.getBat());
        }

        throw new IllegalStateException("승패가 정해지지 않았습니다");
    }

    private static double calculateBattingFromResult(Result result, Double bat) {
        return result.calculateProfit
                .apply(bat);
    }

    private static Double nonProfit() {
        return NON_PROFIT;
    }
}
