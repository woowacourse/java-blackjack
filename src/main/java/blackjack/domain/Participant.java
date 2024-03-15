package blackjack.domain;

import blackjack.domain.state.State;
import java.util.List;

public abstract class Participant {
    private final Name name;
    private final State state;

    public Participant(Name name, State state) {
        this.name = name;
        this.state = state;
    }

    abstract Participant draw(Deck deck);

    abstract Participant stand();

    public final Score calculateHand() {
        return state.calculateHand();
    }

    public boolean canDraw() {
        return !state.isFinished();
    }

    public final Name getName() {
        return name;
    }

    public final State getState() {
        return state;
    }

    public final List<Card> getCards() {
        return state.getHand().getCards();
    }
}
