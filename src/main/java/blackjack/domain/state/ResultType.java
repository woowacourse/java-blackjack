package blackjack.domain.state;

import java.util.Arrays;
import java.util.function.Function;

public enum ResultType {
    BLACKJACK((difference) -> difference > 0, 1.5),
    WIN((difference) -> difference > 0, 1),
    LOSE((difference) -> difference < 0, -1),
    TIE((difference) -> difference == 0, 0);

    private final Function<Integer, Boolean> matcher;
    private final double profitRate;

    ResultType(Function<Integer, Boolean> matcher, double profitRate) {
        this.matcher = matcher;
        this.profitRate = profitRate;
    }

    public static ResultType getResultType(int difference) {
        return Arrays.stream(ResultType.values())
                .filter(resultType -> resultType.matcher.apply(difference))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("불가능한 결과입니다."));
    }

    public double getProfitRate() {
        return profitRate;
    }
}
