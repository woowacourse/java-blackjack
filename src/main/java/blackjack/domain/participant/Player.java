package blackjack.domain.participant;

import blackjack.domain.state.Ready;
import blackjack.domain.state.State;

public class Player extends Participant {

    public Player(String name) {
        this(new Name(name));
    }

    public Player(Name name) {
        this(name, new Ready());
    }

    public Player(Name name, State state) {
        super(name, state);
    }

    @Override
    public boolean shouldReceive() {
        return !isFinished();
    }
}
