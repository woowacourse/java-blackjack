package blackjack.domain.result;

import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum Result {
    WIN("승", (score, opponentScore) -> score > opponentScore),
    DRAW("무", (score, opponentScore) -> score == opponentScore),
    LOSE("패", (score, opponentScore) -> score < opponentScore),
    FAILURE("승부 조회 실패", (score, opponentScore) -> false);

    private final String result;
    private final BiFunction<Integer, Integer, Boolean> checkCondition;

    Result(final String result, BiFunction<Integer, Integer, Boolean> checkCondition) {
        this.result = result;
        this.checkCondition = checkCondition;
    }

    public static Result findResult(final int score, final int opponentScore) {
        return Stream.of(values())
                .filter(resultEnum -> resultEnum.checkCondition.apply(score, opponentScore))
                .findAny()
                .orElse(FAILURE);
    }

    public String getResult() {
        return result;
    }
}
