package domain.participant;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Result {
    LOSE(-1, (playerScore, dealerScore) -> playerScore < dealerScore && !isBust(dealerScore)),
    WIN(1, (playerScore, dealerScore) -> playerScore > dealerScore || isBust(dealerScore)),
    PUSH(0, Integer::equals),
    BLACKJACK(1.5);

    private static final int MAX_SCORE = 21;

    private final double dividendRate;
    private BiPredicate<Integer, Integer> condition;

    Result(double multipleValue) {
        this.dividendRate = multipleValue;
    }

    Result(double multipleValue, BiPredicate<Integer, Integer> condition) {
        this(multipleValue);
        this.condition = condition;
    }

    private static boolean isBust(int score) {
        return score > MAX_SCORE;
    }

    public static Result judgeResult(int playerScore, int dealerScore, boolean isPlayerBlackJack,
        boolean isDealerBlackJack) {

        if (isPlayerBlackJack && !isDealerBlackJack) {
            return Result.BLACKJACK;
        }
        if (!isPlayerBlackJack && isDealerBlackJack || isBust(playerScore)) {
            return Result.LOSE;
        }

        return Arrays.stream(Result.values())
            .filter(result -> result.condition.test(playerScore, dealerScore))
            .findFirst()
            .orElseThrow();
    }

    public double getDividendRate() {
        return dividendRate;
    }
}
