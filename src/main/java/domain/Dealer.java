package domain;

public class Dealer extends BlackjackPlayer {

    private static final int DEALER_STOP_HIT_STANDARD = 17;

    public Dealer() {
        super();
    }

    @Override
    public boolean isDrawable() {
        int sum = calculateCardSum();
        return sum < DEALER_STOP_HIT_STANDARD;
    }
}
