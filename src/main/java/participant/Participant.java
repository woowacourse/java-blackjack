package participant;

public class Participant {

    private State state;

    public boolean wantHit() {
        return state == State.HIT;
    }

    public void changeState(final State state) {
        this.state = state;
    }
}
