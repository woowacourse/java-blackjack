package blackjack.domain.gamer;

import blackjack.domain.state.State;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    public Player(String name, State state) {
        super(name, state);
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    @Override
    public boolean canDraw() {
        return !state.isFinished();
    }
}
