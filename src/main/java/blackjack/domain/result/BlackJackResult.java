package blackjack.domain.result;

import java.util.Arrays;

public enum BlackJackResult {

    WIN(ResultValue.WIN),
    LOSE(ResultValue.LOSE),
    DRAW(ResultValue.DRAW);

    private static final int POSSIBLE_MAX_VALUE = 21;
    private final int value;

    BlackJackResult(int value) {
        this.value = value;
    }

    public static BlackJackResult of(Integer point, Integer otherPoint) {
        if (point > POSSIBLE_MAX_VALUE) {
            return LOSE;
        }
        if (otherPoint > POSSIBLE_MAX_VALUE) {
            return WIN;
        }
        return BlackJackResult.valueOf(point.compareTo(otherPoint));
    }

    private static BlackJackResult valueOf(int value) {
        return Arrays.stream(BlackJackResult.values())
                .filter(result -> result.value == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("옯바른 결과를 찾을 수 없습니다."));
    }

    public BlackJackResult getReverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    private static class ResultValue {
        private static final int WIN = 1;
        private static final int LOSE = -1;
        private static final int DRAW = 0;
    }
}
