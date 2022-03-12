package domain.participant;

import domain.CanAddCardThreshold;

public final class Player extends Participant {
    public Player() {
        super(CanAddCardThreshold.PLAYER_THRESHOLD);
    }
}
