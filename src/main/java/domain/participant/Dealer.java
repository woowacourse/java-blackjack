package domain.participant;

import domain.score.Score;

public final class Dealer extends Participant {
    private static final Score FILL_BOUNDARY_INCLUSIVE = new Score(16);
    private static final Name DEFAULT_DEALER = new Name("딜러");

    public Dealer() {
        super(DEFAULT_DEALER);
    }

    @Override
    public boolean isHittable() {
        return calculateScore().isSmallerOrEqualsTo(FILL_BOUNDARY_INCLUSIVE);
    }
}
