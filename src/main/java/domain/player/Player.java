package domain.player;

import domain.state.State;

public abstract class Player {

    protected final String name;
    protected State state;

    public Player(String name, State state) {
        this.name = name;
        this.state = state;
    }
}
