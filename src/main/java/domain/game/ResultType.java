package domain.game;

import domain.player.hand.Score;
import domain.player.Dealer;
import domain.player.Participant;

import java.util.List;

public enum ResultType {
    VICTORY,
    TIE,
    DEFEAT;

    private static final int TIE_DIFF = 0;
    private static final int MAKE_INDEX = 1;
    private static final List<ResultType> values = List.of(ResultType.VICTORY, ResultType.TIE, ResultType.DEFEAT);

    public static ResultType of(final Dealer dealer, final Participant participant) {
        if (participant.isBust()) {
            return ResultType.DEFEAT;
        }
        if (dealer.isBust()) {
            return ResultType.VICTORY;
        }
        return judgeResult(dealer.score(), participant.score());
    }

    private static ResultType judgeResult(final Score dealerScore, final Score participantScore) {
        final int scoreDiff = dealerScore.scoreGap(participantScore);
        final int index = Integer.compare(scoreDiff, TIE_DIFF) + MAKE_INDEX;

        return values.get(index);
    }
}
