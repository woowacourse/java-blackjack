package model.player;

public class Dealer extends Player {

    private static final int NUMBER_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean receiveCard() {
        return sumCardNumbers() <= NUMBER_THRESHOLD;
    }
}
