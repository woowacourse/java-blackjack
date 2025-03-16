package domain;

import static util.BlackjackConstants.DEALER_NAME;

public class Dealer extends Participant {

    private static final int VALID_DRAW_LIMIT = 16;

    private Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    public static Dealer createEmpty() {
        return new Dealer(Hand.createEmpty());
    }

    public Card getDealCard() {
        return hand.getFirst();
    }

    public boolean isPossibleDraw() {
        return hand.calculateSum() <= VALID_DRAW_LIMIT;
    }
}
