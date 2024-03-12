package blackjack.participant.state.terminated;

import blackjack.game.MatchResult;
import blackjack.participant.Hand;
import blackjack.participant.state.GameState;

public class BustedState extends TerminatedState {

    public BustedState(Hand hand) {
        super(hand);
    }

    @Override
    public MatchResult createMatchResult(GameState opponentState) {
        return MatchResult.LOSE;
    }
}
