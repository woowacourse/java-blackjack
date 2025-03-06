package model.participant;

public class Dealer extends Participant {

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean canReceiveCard() {
        return score() <= 16;
    }
}
