package model.participator;

public class Dealer extends Participator {
    public static final String DEALER_NAME = "딜러";
    private static final int DEALER_CRITERIA_HIT_SCORE = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canReceiveCard() {
        return this.getSum() <= DEALER_CRITERIA_HIT_SCORE;
    }
}
