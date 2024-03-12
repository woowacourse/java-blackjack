package blackjack.player.state.terminated;

import blackjack.game.MatchResult;
import blackjack.player.Hand;
import blackjack.player.state.GameState;

public class BustedState extends TerminatedState {

    public BustedState(Hand hand) {
        super(hand);
    }

    @Override
    public MatchResult createMatchResult(GameState opponentState) {
        return MatchResult.LOSE;
    }
}
