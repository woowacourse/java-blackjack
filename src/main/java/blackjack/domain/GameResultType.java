package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResultType {
    WIN((value, comparedValue) -> value > comparedValue),
    TIE(Integer::equals),
    LOSE((value, comparedValue) -> value < comparedValue);

    private final BiPredicate<Integer, Integer> condition;

    GameResultType(BiPredicate<Integer, Integer> condition) {
        this.condition = condition;
    }

    public static GameResultType find(int value, int comparedValue) {
        return Arrays.stream(GameResultType.values())
                .filter(resultType -> resultType.condition.test(value, comparedValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 비교 값입니다."));
    }
}
