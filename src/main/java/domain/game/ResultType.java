package domain.game;

import domain.player.hand.Score;
import domain.player.Dealer;
import domain.player.Participant;

import java.util.List;
import java.util.function.Function;

public enum ResultType {
    VICTORY(Participant::winBet),
    TIE(Participant::returnBet),
    DEFEAT(Participant::loseBet);

    private static final int TIE_DIFF = 0;
    private static final int MAKE_INDEX = 1;
    private static final List<ResultType> values = List.of(ResultType.VICTORY, ResultType.TIE, ResultType.DEFEAT);

    private final Function<Participant, Integer> calculator;

    ResultType(final Function<Participant, Integer> calculator) {
        this.calculator = calculator;
    }


    public static int of(final Dealer dealer, final Participant participant) {
        if (participant.isBust()) {
            return ResultType.DEFEAT.calculator.apply(participant);
        }
        if (dealer.isBust()) {
            return ResultType.VICTORY.calculator.apply(participant);
        }
        return judgeResult(dealer.getScore(), participant.getScore()).apply(participant);
    }

    private static Function<Participant, Integer> judgeResult(final Score dealerScore, final Score participantScore) {
        final int scoreDiff = dealerScore.scoreGap(participantScore);
        final int index = Integer.compare(scoreDiff, TIE_DIFF) + MAKE_INDEX;

        return values.get(index).calculator;
    }
}
