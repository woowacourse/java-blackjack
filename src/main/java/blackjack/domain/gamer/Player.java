package blackjack.domain.gamer;

import blackjack.domain.state.State;

public class Player extends Participants {

    public Player(String name) {
        super(name);
    }

    public Player(String name, State state) {
        super(name, state);
    }

    public double profit() {
        return state.profit(money);
    }

    @Override
    public boolean canDraw() {
        return !state.isFinished();
    }
}
