package blackjack.domain.participants;

import blackjack.dto.HandStatus;

public class Player extends Participant {

    public Player(final String name) {
        super(name);
    }

    @Override
    public boolean isAbleToHit() {
        return isNotBustNorHasMaxScore();
    }

    @Override
    public HandStatus toHandStatus() {
        return new HandStatus(getName(), cards());
    }
}
