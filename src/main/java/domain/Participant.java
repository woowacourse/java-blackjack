package domain;

public class Participant extends Player {
    public Participant(final Name name) {
        super(name);
    }

    @Override
    public boolean isNotBust() {
        return calculateScore() < 21;
    }
}
