package blackjack.participant.state.terminated;

import blackjack.game.MatchResult;
import blackjack.participant.Hand;
import blackjack.participant.state.GameState;

public class BlackJackState extends TerminatedState {

    public BlackJackState(Hand hand) {
        super(hand);
    }

    @Override
    public MatchResult createMatchResult(GameState opponentState) {
        if (opponentState.isBlackJack()) {
            return MatchResult.TIE;
        }
        return MatchResult.BLACKJACK_WIN;
    }
}
