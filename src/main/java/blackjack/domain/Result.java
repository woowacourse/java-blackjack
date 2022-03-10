package blackjack.domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

public enum Result {

    WIN("승", (dealerScore, participantScore) ->
            (dealerScore < participantScore && participantScore <= 21) || (dealerScore > 21 && participantScore <= 21)),
    DRAW("무승부", (dealerScore, participantScore) ->
            Objects.equals(dealerScore, participantScore) && participantScore <= 21),
    LOSE("패", (dealerScore, participantScore) ->
            (dealerScore > participantScore && dealerScore <= 21) || participantScore > 21);

    private final String name;
    private final BiPredicate<Integer, Integer> condition;

    Result(final String name, final BiPredicate<Integer, Integer> condition) {
        this.name = name;
        this.condition = condition;
    }

    public static Result decide(final int dealerScore, final int participantScore) {
        return Arrays.stream(Result.values())
                .filter(result -> result.condition.test(dealerScore, participantScore))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 승패를 가릴 수 없는 경우입니다."));
    }

    public Result getOpposite() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getName() {
        return this.name;
    }
}
