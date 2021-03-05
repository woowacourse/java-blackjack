package blackjack.domain.user;

import java.util.Arrays;
import java.util.function.IntPredicate;

public enum Status {
    PLAYING(true, i -> 1 < i && i < 21),
    BLACKJACK(false, i -> i == 21),
    BURST(false, i -> 21 < i && i < 31),
    STOP(false, i -> false);

    private static final String NO_MATCH_CONDITION_ERROR_MSG = "점수 조건에 맞는 Status가 없습니다.";

    private boolean canContinueGame;
    private IntPredicate condition;

    Status(boolean canContinueGame, IntPredicate condition) {
        this.canContinueGame = canContinueGame;
        this.condition = condition;
    }

    public static Status of(int totalScore) {
        return Arrays.stream(Status.values())
                .filter(status -> status.condition.test(totalScore))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCH_CONDITION_ERROR_MSG)); //default value
    }

    public boolean canContinueGame() {
        return canContinueGame;
    }
}
