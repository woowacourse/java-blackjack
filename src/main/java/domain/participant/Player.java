package domain.participant;

import domain.score.Score;

public final class Player extends Participant {
    private static final Score BUST_BOUNDARY_EXCLUSIVE = new Score(21);

    public Player(final Name name) {
        super(name);
    }

    public Player(final String name) {
        super(new Name(name));
    }

    @Override
    public boolean isHittable() {
        return calculateScore().isSmallerThan(BUST_BOUNDARY_EXCLUSIVE);
    }

    public void selectStand() {
        this.status = status.selectStand();
    }
}
