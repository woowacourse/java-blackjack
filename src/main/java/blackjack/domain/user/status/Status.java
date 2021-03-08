package blackjack.domain.user.status;

import blackjack.domain.user.Participant;

import java.util.Arrays;
import java.util.function.Predicate;

import static blackjack.domain.BlackjackGame.BLACKJACK_NUMBER;

public enum Status {
    PLAYING(true, Status::playingCondition),
    BLACKJACK(false, Status::blackjackCondition),
    BURST(false, Status::burstCondition),
    STOP(false, score -> false);

    private static final String NO_MATCH_CONDITION_ERROR_MSG = "점수 조건에 맞는 Status가 없습니다.";
    private static final int DOUBLE = 2;
    private static final int MINIMUM_SCORE = 1;
    private static final int MAXIMUM_SCORE = 31;

    private final boolean canContinueGame;
    private final Predicate<Participant> condition;

    Status(boolean canContinueGame, Predicate<Participant> condition) {
        this.canContinueGame = canContinueGame;
        this.condition = condition;
    }

    public static Status of(Participant participant) {
        return Arrays.stream(Status.values())
                .filter(status -> status.condition.test(participant))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCH_CONDITION_ERROR_MSG)); //default value
    }

    public boolean canContinueGame() {
        return canContinueGame;
    }

    private static boolean playingCondition(Participant participant) {
        int score = participant.calculateScore();
        boolean isNotBlackjack = !blackjackCondition(participant);
        return MINIMUM_SCORE < score && score <= BLACKJACK_NUMBER && isNotBlackjack;
    }

    private static boolean blackjackCondition(Participant participant) {
        int score = participant.calculateScore();
        boolean isDoubleCard = participant.isSameHandSize(DOUBLE);
        return score == BLACKJACK_NUMBER && isDoubleCard;
    }

    private static boolean burstCondition(Participant participant) {
        int score = participant.calculateScore();
        return  BLACKJACK_NUMBER < score && score < MAXIMUM_SCORE;
    }
}
