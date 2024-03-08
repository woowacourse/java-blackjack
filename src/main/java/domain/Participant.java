package domain;

public class Participant extends Player {
    private static final int BUST_CONDITION = 21;

    public Participant(final Name name) {
        super(name);
    }

    @Override
    public boolean isNotBust() {
        return calculateScore() < BUST_CONDITION;
    }
}
