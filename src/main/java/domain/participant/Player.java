package domain.participant;

public class Player extends Participant {
    public static final int BLACK_JACK = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canReceive() {
        if (super.score() <= BLACK_JACK) {
            return true;
        }

        return false;
    }
}
