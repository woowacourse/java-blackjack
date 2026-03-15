package blackjack.domain.participants;

import blackjack.domain.card.Hand;

public class Dealer extends Participant {
    private static final Name DEALER_NAME = new Name("딜러");
    private static final int HIT_THRESHOLD = 16;

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    public static Dealer createEmptyHand() {
        return new Dealer(Hand.empty());
    }

    public static boolean isDealerName(Name name) {
        return DEALER_NAME.equals(name);
    }

    @Override
    public boolean canHit() {
        return getScore().isLessThanOrEqual(HIT_THRESHOLD);
    }
}
