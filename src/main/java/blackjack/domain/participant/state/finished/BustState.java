package blackjack.domain.participant.state.finished;

import blackjack.domain.participant.state.PlayState;
import blackjack.domain.result.MatchStatus;

public class BustState extends FinishedState {

    public BustState(final PlayState state) {
        super(state);
    }

    @Override
    public MatchStatus judgeMatchStatus(FinishedState state) {
        return MatchStatus.LOSS;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public String toString() {
        return "BustState{}";
    }

}
