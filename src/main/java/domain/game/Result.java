package domain.game;

import domain.player.Dealer;
import domain.player.Participant;

public enum Result {
    VICTORY,
    TIE,
    DEFEAT;

    private static final int TIE_DIFF = 0;
    private static final int MAKE_INDEX = 1;

    public static Result of(final Dealer dealer, final Participant participant) {
        if (participant.isBust()) {
            return Result.DEFEAT;
        }
        if (dealer.isBust()) {
            return Result.VICTORY;
        }
        return judgeResult(dealer.getScore(), participant.getScore());
    }

    private static Result judgeResult(final int dealerScore, final int participantScore) {
        final int scoreDiff = dealerScore - participantScore;
        final Result[] values = Result.values();
        final int index = Integer.compare(scoreDiff, TIE_DIFF) + MAKE_INDEX;

        return values[index];
    }
}
