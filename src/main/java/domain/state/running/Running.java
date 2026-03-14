package domain.state.running;

import domain.card.Hand;
import domain.score.Score;
import domain.state.Started;
import domain.state.State;
import domain.state.finished.Stay;

public abstract class Running extends Started {

    protected Running(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Score getScore() {
        return hand.getScore();
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }

    @Override
    public Integer getProfit(State DealerState, Integer betCost) {
        throw new IllegalStateException("Running은 수익금 반환을 못합니다!");
    }
}
