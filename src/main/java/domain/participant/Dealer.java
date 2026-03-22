package domain.participant;

import domain.state.HandState;
import domain.state.TurnState;

public final class Dealer extends Participant {
    private static final int DEALER_STAND_SCORE = 17;

    public Dealer() {
        super();
    }

    @Override
    public TurnState getTurnState() {
        if (getHandState() != HandState.STAND) {
            return TurnState.FINISHED;
        }
        if (getScore() < DEALER_STAND_SCORE) {
            return TurnState.HITTING;
        }
        return TurnState.FINISHED;
    }
}
