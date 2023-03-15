package domain.participant;

import domain.score.Score;

public final class Dealer extends Participant {
    private static final Score FILL_BOUNDARY_INCLUSIVE = new Score(16);
    private static final Name DEALER_NAME = new Name("딜러");

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isHittable() {
        return calculateScore().isSmallerOrEqualsTo(FILL_BOUNDARY_INCLUSIVE);
    }
}
