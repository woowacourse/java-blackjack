package blackjack.player.state.playing;

import blackjack.game.MatchResult;
import blackjack.player.Hand;
import blackjack.player.state.GameState;
import blackjack.player.state.terminated.StandState;

public abstract class PlayingState extends GameState {

    protected PlayingState(Hand hand) {
        super(hand);
    }

    @Override
    public GameState stand() {
        return new StandState(hand);
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public MatchResult createMatchResult(GameState opponentState) {
        throw new UnsupportedOperationException("[ERROR] 종료 상태가 아니면 결과를 생성할 수 없습니다.");
    }
}
