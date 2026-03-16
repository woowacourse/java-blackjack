package domain.player;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_REFERENCE_POINT = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public boolean isBelowDrawThreshold() {
        return getTotalScore() <= DEALER_REFERENCE_POINT;
    }

    public String getFirstHand() {
        return hand.getFirstCardInfo();
    }
}
