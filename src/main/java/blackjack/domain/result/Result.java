package blackjack.domain.result;

import static blackjack.constant.Rule.WINNING_SCORE;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

public enum Result {

    WIN("승", (dealerScore, participantScore) ->
            participantScore <= WINNING_SCORE.getValue() &&
                    (dealerScore < participantScore || dealerScore > WINNING_SCORE.getValue())),
    DRAW("무승부", (dealerScore, participantScore) ->
            participantScore <= WINNING_SCORE.getValue() &&
                    Objects.equals(dealerScore, participantScore)),
    LOSE("패", (dealerScore, participantScore) ->
            participantScore > WINNING_SCORE.getValue() ||
                    (dealerScore > participantScore && dealerScore <= WINNING_SCORE.getValue()));

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
