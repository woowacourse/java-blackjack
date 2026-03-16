package domain.state;

import domain.Result;
import domain.card.Hand;

public abstract class State {
    protected final Hand hand;

    public State(Hand hand) {
        this.hand = hand;
    }

    public abstract Result judge(State state);
}
