package domain.participant;

import domain.state.Hit;

public class Player extends Participant {

    public Player(Name name) {
        super(name, new Hit(new Hand()));
    }

    @Override
    public boolean canReceive() {
        return !isFinished();
    }
}
