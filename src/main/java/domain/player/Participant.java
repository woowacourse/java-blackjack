package domain.player;

public class Participant extends Player {
    private static final int HIT_THRESHOLD = 21;

    public Participant(final Name name) {
        super(name);
    }

    @Override
    public boolean isBust() {
        return calculateScore() > PERFECT_SCORE;
    }


    @Override
    public boolean isNotBust() {
        return !isBust();
    }

    @Override
    public boolean canHit() {
        return calculateScore() < HIT_THRESHOLD;
    }

    @Override
    public boolean canNotHit() {
        return !canHit();
    }
}
