package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

public enum Result {
    WIN("승", (playerScore, dealerScore) -> playerScore > dealerScore),
    STAND_OFF("무", Integer::equals),
    LOSE("패", (playerScore, dealerScore) -> playerScore < dealerScore);

    private final String result;
    private final BiPredicate<Integer, Integer> scoreComparePredicate;

    Result(String result, BiPredicate<Integer, Integer> scoreComparePredicate) {
        this.result = result;
        this.scoreComparePredicate = scoreComparePredicate;
    }

    public static Result decide(int playerScore, int dealerScore) {
        return Arrays.stream(values())
                .filter(value -> value.scoreComparePredicate.test(playerScore, dealerScore))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("승패 결과 조건에 매치되지 않습니다."));
    }

    public static Map<Result, Integer> countByResults(List<Result> results) {
        return Arrays.stream(values())
                .collect(Collectors.toMap(result -> result, result -> result.count(results)));
    }

    public Result reverse(Result result) {
        if (result.equals(WIN)) {
            return LOSE;
        }
        if (result.equals(LOSE)) {
            return WIN;
        }
        return result;
    }

    public int count(List<Result> results) {
        return (int) results.stream()
                .filter(this::equals)
                .count();
    }

    public String getResult() {
        return this.result;
    }
}
