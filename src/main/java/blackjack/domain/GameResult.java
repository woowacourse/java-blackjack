package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {

    WIN("승", 0, (score, otherScore) -> score > otherScore),
    TIE("무", 0, (score, otherScore) -> score == otherScore),
    LOSE("패", -2, (score, otherScore) -> score < otherScore),
    BLACKJACK("블랙잭", 0.5, (score, otherScore) -> score == 21);

    private final String value;
    private final double bettingMoneyResult;
    private final BiPredicate<Integer, Integer> condition;

    GameResult(String value, double bettingMoneyResult, BiPredicate<Integer, Integer> condition) {
        this.value = value;
        this.bettingMoneyResult = bettingMoneyResult;
        this.condition = condition;
    }

    public static GameResult of(int score, int otherScore) {
        return Arrays.stream(values())
                .filter(gameResult -> gameResult.condition.test(score, otherScore))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("결과가 정상적으로 나오지 않았습니다."));
    }

    public String getValue() {
        return value;
    }

    public double getBettingMoneyResult() {
        return bettingMoneyResult;
    }
}
