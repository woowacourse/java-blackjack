package domain.player;

import java.util.Arrays;
import java.util.function.Predicate;

public enum HandsState {

    BUST(x -> x > 21),
    BLACKJACK(x -> x == 21),
    IN_PLAY(x -> x <= 21),
    ;

    private final Predicate<Integer> calculator;

    HandsState(Predicate<Integer> calculator) {
        this.calculator = calculator;
    }

    public static HandsState from(int score) {
        return Arrays.stream(HandsState.values())
                .filter(i->i.calculator.test(score))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("값이 올바르지 않습니다."));
    }
}
