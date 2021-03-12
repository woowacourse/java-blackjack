package blackjack.domain.user;

import java.util.Arrays;
import java.util.function.IntPredicate;

public enum Status {
    Hit(true, i -> i < Status.BLACKJACK_SCORE),
    STAY(false, i -> i == Status.BLACKJACK_SCORE),
    BURST(false, i -> i > Status.BLACKJACK_SCORE),
    BLACKJACK(false, i -> false);

    public static final int BLACKJACK_SCORE = 21;
    private static final String NO_MATCH_CONDITION_ERROR_MSG = "점수 조건에 맞는 Status가 없습니다.";
    public static final int BLACKJACK_CARD_COUNT = 2;

    private boolean canContinueGame;
    private IntPredicate condition;

    Status(boolean canContinueGame, IntPredicate condition) {
        this.canContinueGame = canContinueGame;
        this.condition = condition;
    }

    public static Status of(Hand hand) {
        if (hand.size() == BLACKJACK_CARD_COUNT && hand.calculateScore() == BLACKJACK_SCORE) {
            return BLACKJACK;
        }
        return Arrays.stream(Status.values())
                .filter(status -> status.condition.test(hand.calculateScore()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCH_CONDITION_ERROR_MSG));
    }

    public boolean canContinueGame() {
        return canContinueGame;
    }
}
