package domain.participant;

import domain.Result;
import domain.card.Card;
import domain.state.State;

public abstract class Participant {
    protected State state;

    protected Participant(State state) {
        this.state = state;
    }

    public void draw(Card card) {
        state = state.draw(card);
    }

    public void stay() {
        state = state.stay();
    }

    public Result judge(Participant target) {
        return state.judge(target.state);
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public State getState() {
        return state;
    }
}
