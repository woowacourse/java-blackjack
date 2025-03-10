package domain;

public class Dealer extends BlackjackParticipant {

    private static final String DEALER_NAME = "딜러";
    private final int DEALER_STOP_HIT_STANDARD = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public boolean isDrawable() {
        int sum = hand.calculateCardSum(DEALER_STOP_HIT_STANDARD);
        return sum <= DEALER_STOP_HIT_STANDARD;
    }
}
