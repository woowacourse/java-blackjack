package domain;

public class Participant extends Player {
    public Participant(final Name name) {
        super(name);
    }

    @Override
    public boolean alive() {
        return calculateScore() < 21;
    }
}
