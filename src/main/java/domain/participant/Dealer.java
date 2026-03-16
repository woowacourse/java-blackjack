package domain.participant;

public class Dealer extends Participant {
    private static final int DEALER_DRAW_BOUND = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public String getFirstCardName() {
        return getFirstName();
    }

    @Override
    public boolean canDraw() {
        return getScore().isLessThanOrEqual(DEALER_DRAW_BOUND);
    }
}
