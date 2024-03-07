package domain.participant;

public class Player extends Participant {

    public Player(Name name) {
        super(name);
    }

    @Override
    public boolean isReceivable() {
        return !isBusted();
    }
}
