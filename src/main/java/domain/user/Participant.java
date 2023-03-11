package domain.user;

import domain.card.Card;
import domain.state.Ready;
import domain.state.State;

public class Participant {

    private final Name name;
    private State state;

    public Participant(String playerName) {
        this.name = new Name(playerName);
        state = new Ready();
    }

    public State hit(Card card) {
        return state = state.draw(card);
    }

    public State stay() {
        return state = state.stay();
    }

    public Name getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name=" + name +
                ", state=" + state +
                '}';
    }
}
