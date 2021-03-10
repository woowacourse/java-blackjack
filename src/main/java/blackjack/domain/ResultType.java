package blackjack.domain;

import java.util.Arrays;
import java.util.function.Function;

public enum ResultType {
    WIN((difference) -> difference > 0, "승", 1),
    LOSE((difference) -> difference < 0, "패", -1),
    TIE((difference) -> difference == 0, "무", 0);

    private final Function<Integer, Boolean> matcher;
    private final String name;
    private final double profitRate;

    ResultType(Function<Integer, Boolean> matcher, String name, double profitRate) {
        this.matcher = matcher;
        this.name = name;
        this.profitRate = profitRate;
    }

    public static ResultType getResultType(int difference) {
        return Arrays.stream(ResultType.values())
                .filter(resultType -> resultType.matcher.apply(difference))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("불가능한 결과입니다."));
    }

    public String getName() {
        return name;
    }

    public double getProfitRate() {
        return profitRate;
    }

    public ResultType opposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return TIE;
    }
}
