package blackjack.domain;

import java.util.Arrays;

public enum ProfitTable {
    BLACKJACK_WIN(ResultType.BLACKJACK_WIN, 1.5),
    WIN(ResultType.WIN, 1),
    DRAW(ResultType.DRAW, 0),
    LOSE(ResultType.LOSE, -1);

    private final ResultType resultType;
    private final double profitRatio;

    ProfitTable(ResultType resultType, double profitRatio) {
        this.resultType = resultType;
        this.profitRatio = profitRatio;
    }

    public static Money translateBettingMoney(ResultType resultType, Money bettingMoney) {
        double ratio = Arrays.stream(values()).parallel()
                .filter(item -> item.resultType == resultType)
                .map(item -> item.profitRatio)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("승패 결과에 맞는 배당율이 없습니다."));
        return bettingMoney.multiply(ratio);
    }
}
