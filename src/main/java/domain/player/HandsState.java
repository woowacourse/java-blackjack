package domain.player;

import java.util.Arrays;
import java.util.function.Predicate;

public enum HandsState {

    BUST(score -> score > 21),
    MAX_SCORE(score -> score == 21),
    IN_PLAY(score -> score < 21),
    ;

    private final Predicate<Integer> calculator;

    HandsState(Predicate<Integer> calculator) {
        this.calculator = calculator;
    }

    public static HandsState from(final int score) {
        return Arrays.stream(HandsState.values())
                .filter(i -> i.calculator.test(score))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("점수 값이 올바르지 않습니다."));
    }
}
