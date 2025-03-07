package blackjack.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResultType {

    WIN((value, comparedValue) -> value > comparedValue, "승"),
    TIE(Integer::equals, "무"),
    LOSE((value, comparedValue) -> value < comparedValue, "패");

    private final BiPredicate<Integer, Integer> condition;
    private final String description;

    GameResultType(BiPredicate<Integer, Integer> condition, String description) {
        this.condition = condition;
        this.description = description;
    }

    public static GameResultType find(int value, int comparedValue) {
        return Arrays.stream(GameResultType.values())
                .filter(resultType -> resultType.condition.test(value, comparedValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 비교 값입니다."));
    }

    public GameResultType getOppositeType() {
        if (this.equals(WIN)) {
            return LOSE;
        }

        if (this.equals(LOSE)) {
            return WIN;
        }

        return TIE;
    }

    public String getDescription() {
        return description;
    }
}
