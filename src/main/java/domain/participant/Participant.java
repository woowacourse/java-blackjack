package domain.participant;

import domain.Result;
import domain.Score;
import domain.card.Card;
import domain.state.State;
import java.util.List;

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

    public Score totalSum() {
        return state.totalSum();
    }

    public List<Card> getCards() {
        return state.hand().getCards();
    }
}
