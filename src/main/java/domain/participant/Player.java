package domain.participant;

public class Player extends Participant {

    private static final int BLACK_JACK_COUNT = 21;

    public Player(Name name) {
        super(name);
    }

    @Override
    boolean canHit() {
        return hands.calculateScore() <= BLACK_JACK_COUNT;
    }
}
