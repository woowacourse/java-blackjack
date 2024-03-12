package blackjack.participant.state.playing;

import blackjack.game.MatchResult;
import blackjack.participant.Hand;
import blackjack.participant.state.GameState;
import blackjack.participant.state.terminated.StandState;

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
    public MatchResult createMatchResult(GameState other) {
        throw new UnsupportedOperationException("[ERROR] 종료 상태가 아니면 결과를 생성할 수 없습니다.");
    }
}
