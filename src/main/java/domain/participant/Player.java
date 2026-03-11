package domain.participant;

import static domain.BlackjackRule.BLACK_JACK;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        return super.score() <= BLACK_JACK;
    }
}
