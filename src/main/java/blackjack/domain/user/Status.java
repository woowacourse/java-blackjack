package blackjack.domain.user;

import java.util.Arrays;
import java.util.function.IntPredicate;

public enum Status {
    PLAYING(true, i -> i < Constants.BLACKJACK),
    BLACKJACK(false, i -> i == Constants.BLACKJACK),
    BURST(false, i -> Constants.BLACKJACK < i),
    STOP(false, i -> false);

    private static class Constants {
        public static final String NO_MATCH_CONDITION_ERROR_MSG = "점수 조건에 맞는 Status가 없습니다.";
        public static final int BLACKJACK = 21;
    }

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
                .orElseThrow(() -> new IllegalArgumentException(Constants.NO_MATCH_CONDITION_ERROR_MSG));
    }

    public boolean canContinueGame() {
        return canContinueGame;
    }
}
