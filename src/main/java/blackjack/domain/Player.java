package blackjack.domain;

import blackjack.domain.state.InitialState;
import blackjack.domain.state.State;

public class Player {
    private final Name name;
    private final State state;

    private Player(Name name, State state) {
        this.name = name;
        this.state = state;
    }

    public static Player createInitialStatePlayer(Name name) {
        return new Player(name, new InitialState());
    }

    public Player draw(Deck deck) {
        State newState = state.draw(deck);
        return new Player(name, newState);
    }

    public Player stand() {
        State newState = state.stand();
        return new Player(name, newState);
    }

    public Score calculateHand() {
        return state.calculateHand();
    }

    public boolean canDraw() {
        return !state.isFinished();
    }

    public Name getName() {
        return name;
    }

    public State getState() {
        return state;
    }
}
