package blackjack.domain.participant;

public final class Dealer extends Participant {

    protected static final String DEALER_NAME = "딜러";
    private static final int DEALER_CAN_DRAW_THRESHOLD = 17;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean canDraw() {
        return getScore() < DEALER_CAN_DRAW_THRESHOLD;
    }

}
