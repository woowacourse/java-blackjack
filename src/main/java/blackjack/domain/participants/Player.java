package blackjack.domain.participants;

import blackjack.dto.HandStatus;

public class Player extends Participant {

    public Player(final String name) {
        super(name);
    }

    @Override
    public boolean isAvailable() {
        return isSafe();
    }

    @Override
    public HandStatus toHandStatus() {
        return new HandStatus(getName(), cards());
    }
}
