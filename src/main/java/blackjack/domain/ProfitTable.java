package blackjack.domain;

import java.util.Arrays;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public enum ProfitTable {
    BLACKJACK_WIN(ResultType.BLACKJACK_WIN, 1.5),
    WIN(ResultType.WIN, 1),
    DRAW(ResultType.DRAW, 0),
    LOSE(ResultType.LOSE, -1);

    private static final Map<ResultType, Double> searchMap;

    static {
        searchMap = Arrays.stream(values())
                .collect(toMap(item -> item.resultType, item -> item.profitRatio));
    }

    private final ResultType resultType;
    private final double profitRatio;

    ProfitTable(ResultType resultType, double profitRatio) {
        this.resultType = resultType;
        this.profitRatio = profitRatio;
    }

    public static double translateBettingMoney(ResultType resultType, Money bettingMoney) {
        return bettingMoney.toLong() * searchMap.get(resultType);
    }
}
