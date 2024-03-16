package domain.participant;

public class Player extends Participant {
    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isNotFinished() {
        return !getCards().isBust();
    }
}
