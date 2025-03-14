package participant;

import card.Hand;

public class Dealer extends Participant {
    private static final int VALID_DRAW_LIMIT = 16;

    public Dealer(Hand hand) {
        super(hand);
    }

    public static Dealer createEmptyHand() {
        return new Dealer(Hand.createEmpty());
    }

    public boolean shouldDrawCard() {
        return getScore().isLessOrEqualThen(VALID_DRAW_LIMIT);
    }

    @Override
    public Hand openInitialHand() {
        return new Hand(hand.getFirst());
    }
}
