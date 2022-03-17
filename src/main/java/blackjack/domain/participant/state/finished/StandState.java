package blackjack.domain.participant.state.finished;

import blackjack.domain.participant.state.PlayState;
import blackjack.domain.result.MatchStatus;

public class StandState extends FinishedState {

    public StandState(final PlayState state) {
        super(state);
    }

    @Override
    public MatchStatus judgeMatchStatus(FinishedState otherState) {
        final int thisScore = this.getScore();
        final int otherScore = otherState.getScore();
        if (otherState.isBust() || thisScore > otherScore) {
            return MatchStatus.WIN;
        }
        if (otherState.isBlackjack() || (thisScore < otherScore)) {
            return MatchStatus.LOSS;
        }
        return MatchStatus.DRAW;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public String toString() {
        return "StandState{}";
    }

}
