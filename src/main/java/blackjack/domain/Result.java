package blackjack.domain;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public enum Result {

    BLACK_JACk_WIN("승", (Player player, Dealer dealer)
            -> (player.isFirstTurnBackJack() && dealer.isNotBlackJack())
            , (money) -> 1.5 * money
    ),
    WIN("승", (Player player, Dealer dealer)
            -> (dealer.isBurst() && player.isNotBurst())
            || player.isNotBurst() && (player.getHandsScore() > dealer.getHandsScore())
            , (moeny) -> (moeny)
    ),
    LOSE("패", (Player player, Dealer dealer)
            -> player.isBurst()
            || player.isNotBurst() && (player.getHandsScore() < dealer.getHandsScore())
            , (money) -> (-1 * money)
    ),
    DRAW("무", (Player player, Dealer dealer)
            -> (dealer.isNotBurst() && player.isNotBurst())
            && (dealer.getHandsScore() == player.getHandsScore())
            , money -> Result.nonProfit()
    );

    private static final Double NON_PROFIT = 0.0;
    private final String result;
    private final BiFunction<Player, Dealer, Boolean> resultCondition;
    private final Function<Double, Double> calculateProfit;

    Result(String result, BiFunction<Player, Dealer, Boolean> resultCondition
            , Function<Double, Double> calculateProfit) {
        this.result = result;
        this.resultCondition = resultCondition;
        this.calculateProfit = calculateProfit;
    }

    public static Result reverseResult(Result result) {
        if (result == WIN) {
            return LOSE;
        }
        if (result == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public static Result findResult(Player player, Dealer dealer) {
        Optional<Result> targetResult = Stream.of(values())
                .filter(result -> result.resultCondition.apply(player, dealer))
                .findAny();

        if (targetResult.isPresent()) {
            return targetResult.get();
        }

        throw new IllegalStateException("승패가 정해지지 않았습니다");
    }

//    public static int getProfitByResult(Result result, Batting batting) {
//        return result.calculateProfit
//                .apply(batting.getBat());
//    }

    private static Double nonProfit() {
        return NON_PROFIT;
    }

    public String getResult() {
        return result;
    }

}
