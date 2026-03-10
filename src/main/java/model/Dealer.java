package model;

public class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";
    private static final int MINIMUM_STAND_SCORE = 16;
    private static final int INITIAL_DISPENSE_COUNT = 2;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() <= MINIMUM_STAND_SCORE;
    }
}
