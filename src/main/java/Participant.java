public class Participant extends Player {
    private static final int BLACK_JACK_COUNT = 21;
    private Name name;

    public Participant(Name name) {
        this.name = name;
    }

    public boolean canHit() {
        return calculateScore() <= BLACK_JACK_COUNT;
    }
}
