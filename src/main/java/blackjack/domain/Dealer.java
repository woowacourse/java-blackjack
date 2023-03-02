package blackjack.domain;

public class Dealer extends Participant{
    private static final int DEALER_MAX_HITTABLE_POINT = 16;
    private static final String DEFAULT_NAME = "딜러";

    protected Dealer(final ParticipantCards cards) {
        super(cards, DEFAULT_NAME);
    }

    @Override
    protected boolean isHittable() {
        return getTotalPoint() <= DEALER_MAX_HITTABLE_POINT;
    }
}
