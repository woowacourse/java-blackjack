package blackjack.domain.participant.state.finished;

import blackjack.domain.participant.state.PlayState;
import blackjack.domain.result.MatchStatus;

public class BlackjackState extends FinishedState {

    public BlackjackState(final PlayState state) {
        super(state);
    }

    @Override
    public MatchStatus judgeMatchStatus(FinishedState state) {
        if (state.isBlackjack()) {
            return MatchStatus.DRAW;
        }
        return MatchStatus.BLACKJACK;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public String toString() {
        return "BlackjackState{}";
    }

}
