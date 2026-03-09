package domain.player;

import domain.Participant;

public class Player extends Participant {
    private final static int BUST_THRESHOLD = 21;

    public Player(Name name) {
        super(name);
    }

    @Override
    public boolean canReceive() {
        return getScore() <= BUST_THRESHOLD;
    }
}
