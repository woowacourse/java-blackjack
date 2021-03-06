package blackjack.domain.user.status;

import java.util.Arrays;
import java.util.function.IntPredicate;

public enum Status {
    PLAYING(true, score -> 1 < score && score < 21),
    BLACKJACK(false, score -> score == 21),
    BURST(false, score -> 21 < score && score < 31),
    STOP(false, score -> false);

    private static final String NO_MATCH_CONDITION_ERROR_MSG = "점수 조건에 맞는 Status가 없습니다.";

    private final boolean canContinueGame;
    private final IntPredicate condition;

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
