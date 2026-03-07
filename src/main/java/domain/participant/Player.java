package domain.participant;

public class Player extends Participant {

    public static final int BLAKC_JACK = 21;

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        if (super.score() <= BLAKC_JACK) {
            return true;
        }

        return false;
    }
}
