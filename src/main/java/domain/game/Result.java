package domain.game;

import domain.card.Score;
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
        return judgeResult(dealer.score(), participant.score());
    }

    private static Result judgeResult(final Score dealerScore, final Score participantScore) {
        final int scoreDiff = dealerScore.scoreGap(participantScore);
        final Result[] values = Result.values();
        final int index = Integer.compare(scoreDiff, TIE_DIFF) + MAKE_INDEX;

        return values[index];
    }
}
