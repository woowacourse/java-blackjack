package blackjack.domain.gameresult;

import java.util.function.UnaryOperator;

public enum Result {

    BLACKJACK_WIN(money -> 1.5 * money),
    WIN(money -> money),
    LOSE(money -> -1 * money),
    DRAW(money -> Result.nonProfit());

    private static final Double NON_PROFIT = 0.0;
    private final UnaryOperator<Double> calculateProfit;

    Result(UnaryOperator<Double> calculateProfit) {
        this.calculateProfit = calculateProfit;
    }

    public static double calculateProfit(Result result, double batting) {
        return result.calculateProfit
                .apply(batting);
    }

    private static Double nonProfit() {
        return NON_PROFIT;
    }
}
