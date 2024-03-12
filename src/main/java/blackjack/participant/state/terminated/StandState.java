package blackjack.participant.state.terminated;

import blackjack.game.MatchResult;
import blackjack.participant.Hand;
import blackjack.participant.Score;
import blackjack.participant.state.GameState;

public class StandState extends TerminatedState {

    public StandState(Hand hand) {
        super(hand);
    }

    @Override
    public MatchResult createMatchResult(GameState opponentState) {
        if (opponentState.isBusted()) {
            return MatchResult.NORMAL_WIN;
        }
        return createMatchResultByScore(opponentState);
    }

    private MatchResult createMatchResultByScore(GameState dealerState) {
        Score playerScore = getScore();
        Score dealerScore = dealerState.getScore();
        if (playerScore.isGreaterThan(dealerScore)) {
            return MatchResult.NORMAL_WIN;
        }
        if (dealerScore.isGreaterThan(playerScore)) {
            return MatchResult.LOSE;
        }
        return MatchResult.TIE;
    }
}
