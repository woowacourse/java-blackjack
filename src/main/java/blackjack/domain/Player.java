package blackjack.domain;

import blackjack.domain.state.InitialState;
import blackjack.domain.state.State;

public class Player extends Participant {

    public Player(Name name, State state) {
        super(name, state);
    }

    public static Player createInitialStatePlayer(Name name) {
        return new Player(name, new InitialState());
    }

    @Override
    public Player draw(Deck deck) {
        State newState = getState().draw(deck);
        return new Player(getName(), newState);
    }

    @Override
    public Player stand() {
        State newState = getState().stand();
        return new Player(getName(), newState);
    }
}
