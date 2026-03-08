package domain;

import java.util.List;

public class Dealer extends Participant{
    private static final int DEALER_CARD_SUM_THRESHOLD=16;

    private Dealer(Hand hand) {
        super(hand);
    }

    public static Dealer from(Hand hand) {
        return new Dealer(hand);
    }


    public boolean checkThreshold() {
        return getHand().getScore().getValue() <= DEALER_CARD_SUM_THRESHOLD;
    }


}
