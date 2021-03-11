package blackjack.domain.result;

import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum Result {
    BLACKJACK_WIN("승", (score, opponentScore) -> false, 1.5),
    WIN("승", (score, opponentScore) -> score > opponentScore, 1),
    DRAW("무", (score, opponentScore) -> score == opponentScore, 0),
    LOSE("패", (score, opponentScore) -> score < opponentScore, -1),
    FAILURE("승부 조회 실패", (score, opponentScore) -> false, 0);

    private final String result;
    private final BiFunction<Integer, Integer, Boolean> checkCondition;
    private final double profitRate;

    Result(final String result, BiFunction<Integer, Integer, Boolean> checkCondition, double profitRate) {
        this.result = result;
        this.checkCondition = checkCondition;
        this.profitRate = profitRate;
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

    public double getProfitRate() {
        return profitRate;
    }
}
