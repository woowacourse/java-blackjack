package blackjack.domain.result;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum GameResultType {

    WIN((value, comparedValue) -> value > comparedValue),
    TIE(Integer::equals),
    LOSE((value, comparedValue) -> value < comparedValue);

    private final BiPredicate<Integer, Integer> matchCondition;

    GameResultType(BiPredicate<Integer, Integer> matchCondition) {
        this.matchCondition = matchCondition;
    }

    public static GameResultType find(int value, int comparedValue) {
        return Arrays.stream(GameResultType.values())
                .filter(resultType -> resultType.matchCondition.test(value, comparedValue))
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
}
