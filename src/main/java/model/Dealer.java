package model;

public class Dealer extends Player {

    private final static String DEALER_NAME = "딜러";
    private static final int MINIMUM_STAND_SCORE = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() <= MINIMUM_STAND_SCORE;
    }
}
