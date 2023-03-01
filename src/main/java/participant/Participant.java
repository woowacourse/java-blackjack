package participant;

public class Participant {

    private final Name name;
    private State state;

    public Participant(final Name name) {
        this.name = name;
    }

    public boolean wantHit() {
        return state == State.HIT;
    }

    public void changeState(final State state) {
        this.state = state;
    }

    public Name name() {
        return name;
    }
}
