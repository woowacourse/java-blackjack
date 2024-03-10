package domain.participant;

public class Dealer extends Participant {
    private static final int MAX_DEALER_HAND_VALUE = 16;

    public Dealer(final Hand hand) {
        super(hand);
    }

    public static Dealer init() {
        return new Dealer(Hand.init());
    }

    @Override
    public boolean isDrawable() {
        return hand.getTotalScore().isLowerOrEqual(MAX_DEALER_HAND_VALUE);
    }
}
