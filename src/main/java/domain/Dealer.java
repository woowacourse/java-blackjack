package domain;

public class Dealer extends Participant{
    private static final int DEALER_CARD_SUM_THRESHOLD = 16;

    private Dealer(Hand hand) {
        super(hand);
    }

    public static Dealer from(Hand hand) {
        return new Dealer(hand);
    }

    public boolean checkThreshold() {
        return getHand().getScore().value() <= DEALER_CARD_SUM_THRESHOLD;
    }
}
