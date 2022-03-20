package domain.participant;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    WIN(1, (playerScore, dealerScore) -> playerScore > dealerScore),
    LOSE(-1, (playerScore, dealerScore) -> playerScore < dealerScore),
    PUSH(0, Integer::equals),
    BLACKJACK(1.5);

    private final double dividendRate;
    private BiPredicate<Integer, Integer> condition;

    Result(double multipleValue) {
        this.dividendRate = multipleValue;
    }

    Result(double multipleValue, BiPredicate<Integer, Integer> condition) {
        this(multipleValue);
        this.condition = condition;
    }

    public static Result judgeResult(int playerScore, int dealerScore) {
        return Arrays.stream(Result.values())
            .filter(result -> result.condition.test(playerScore, dealerScore))
            .findFirst()
            .orElseThrow();
    }

    public double getDividendRate() {
        return dividendRate;
    }
}
