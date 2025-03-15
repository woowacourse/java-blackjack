package domain.participant;

public class Player extends Participant {

    public Player(final String name) {
        super(name);
    }

    @Override
    public boolean isPlayer() {
        return true;
    }
}
