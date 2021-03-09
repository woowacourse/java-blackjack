package blackjack.domain;

import blackjack.domain.participants.Participant;
import java.util.Arrays;
import java.util.function.Function;

public enum ResultType {
    WIN((difference) -> difference > 0, "승", 1),
    LOSE((difference) -> difference < 0, "패", -1),
    TIE((difference) -> difference == 0, "무", 0);

    private final Function<Integer, Boolean> matcher;
    private final String name;
    private final double profitMultiplier;

    ResultType(Function<Integer, Boolean> matcher, String name, double profitMultiplier) {
        this.matcher = matcher;
        this.name = name;
        this.profitMultiplier = profitMultiplier;
    }

    public static ResultType getResultTypeByScore(Participant participant1,
        Participant participant2) {
        return Arrays.stream(ResultType.values())
            .filter(resultType -> resultType.matcher.apply(participant1.getScore() - participant2
                .getScore()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("불가능한 결과입니다."));
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

    public String getName() {
        return name;
    }

    public double getProfitMultiplier() {
        return profitMultiplier;
    }
}
