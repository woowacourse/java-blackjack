package blackjack.domain.gameresult;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static blackjack.domain.gameresult.Result.*;

public enum ResultProfit {

    BLACKJACK_WIN_PROFIT(BLACKJACK_WIN, money -> Profit.maxProfitRate() * money),
    WIN_PROFIT(WIN, money -> money),
    LOSE_PROFIT(LOSE, money -> Profit.minProfitRate() * money),
    DRAW_PROFIT(DRAW, money -> ResultProfit.nonProfit());

    private static final double NON_PROFIT = 0.0;
    private final Result result;
    private final UnaryOperator<Double> calculateProfit;

    ResultProfit(Result result, UnaryOperator<Double> calculateProfit) {
        this.result = result;
        this.calculateProfit = calculateProfit;
    }

    public static double calculateProfit(Result targetResult, double batting) {
        return findProfitCalc(targetResult).apply(batting);
    }

    private static UnaryOperator<Double> findProfitCalc(Result targetResult) {
        ResultProfit resultProfit = Stream.of(values())
                .filter(rp -> rp.result == targetResult)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("경기 결과와 매칭되는 수익 반환식이 없습니다."));
        return resultProfit.calculateProfit;
    }

    private static Double nonProfit() {
        return NON_PROFIT;
    }
}
