package model.participant;

public class Dealer extends Participant {

    public static final int DEALER_MAX_NUMBER_FOR_BUST = 16;

    public Dealer() {
        super("딜러");
    }

    @Override
    public boolean canReceiveCard() {
        return score() <= DEALER_MAX_NUMBER_FOR_BUST;
    }
}
