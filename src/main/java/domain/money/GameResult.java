package domain.money;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResult {
    WIN((current, opponent) -> current > opponent, 1),
    LOSE((current, opponent) -> current < opponent, -1),
    DRAW(Integer::equals, 0);

    private final BiPredicate<Integer, Integer> condition;

    private final int benefitRatio;

    GameResult(BiPredicate<Integer, Integer> condition, int benefitRatio) {
        this.condition = condition;
        this.benefitRatio = benefitRatio;
    }

    public static GameResult compare(int current, int opponent) {
        return Arrays.stream(values())
                .filter(gameResult -> gameResult.condition.test(current, opponent))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("입력에 따른 결과가 존재하지 않습니다."));
    }

    public int timesBenefitRatio(int value) {
        return value * benefitRatio;
    }
}
