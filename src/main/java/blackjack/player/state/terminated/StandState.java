package blackjack.player.state.terminated;

import blackjack.game.MatchResult;
import blackjack.player.Hand;
import blackjack.player.Score;
import blackjack.player.state.GameState;

public class StandState extends TerminatedState {

    public StandState(Hand hand) {
        super(hand);
    }

    @Override
    public MatchResult createMatchResult(GameState opponentState) {
        if (opponentState.isBusted()) {
            return MatchResult.NORMAL_WIN;
        }
        if (opponentState.isBlackJack()) {
            return MatchResult.LOSE;
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
