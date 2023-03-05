package domain.game;

import domain.player.Dealer;
import domain.player.Participant;

public enum Result {
    VICTORY,
    TIE,
    DEFEAT;

    public static Result of(final Dealer dealer, final Participant participant) {
        if (participant.isBurst()) {
            return Result.DEFEAT;
        }
        if (dealer.isBurst()) {
            return Result.VICTORY;
        }
        return judgeResult(dealer.getScore(), participant.getScore());
    }

    private static Result judgeResult(final int dealerScore, final int participantScore) {
        final Result[] values = Result.values();
        final int index = Math.min(Math.max(dealerScore - participantScore, -1), 1) + 1;

        return values[index];
    }
}
