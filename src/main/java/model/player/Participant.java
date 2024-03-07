package model.player;

public class Participant extends Player {

    public Participant(String name) {
        super(name);
    }

    @Override
    public boolean receiveCard() {
        return getScore() <= MAXIMUM_SUM;
    }
}
