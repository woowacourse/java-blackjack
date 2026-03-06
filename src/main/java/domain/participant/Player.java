package domain.participant;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canDraw() {
        if (super.score() <= 21) {
            return true;
        }

        return false;
    }
}
