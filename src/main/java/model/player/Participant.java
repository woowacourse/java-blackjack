package model.player;

public class Participant extends Player {

    public Participant(String name) {
        super(name);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public boolean receiveCard() {
        return sumCardNumbers() <= MAXIMUM_SUM;
    }
}
