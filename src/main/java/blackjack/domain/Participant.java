package blackjack.domain;

import blackjack.domain.state.State;

public abstract class Participant {
    private final Name name;
    private final State state;

    public Participant(Name name, State state) {
        this.name = name;
        this.state = state;
    }

    abstract Participant draw(Deck deck);

    abstract Participant stand();

    final Score calculateHand() {
        return state.calculateHand();
    }

    final public boolean canDraw() {
        return !state.isFinished();
    }

    final public Name name() {
        return name;
    }

    final public State state() {
        return state;
    }
}
