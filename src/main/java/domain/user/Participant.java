package domain.user;

import domain.card.Card;
import domain.state.Ready;
import domain.state.State;

import java.util.List;

public class Participant {

    private final Name name;
    private State state;

    public Participant(final String playerName) {
        this.name = new Name(playerName);
        state = new Ready();
    }

    public State hit(final Card card) {
        return state = state.draw(card);
    }

    public State stay() {
        return state = state.stay();
    }

    public String name() {
        return name.getValue();
    }

    public State getState() {
        return state;
    }

    public List<Card> cards() {
        return getState().cards();
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name=" + name +
                ", state=" + state +
                '}';
    }
}
