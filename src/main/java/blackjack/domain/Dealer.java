package blackjack.domain;

public class Dealer extends Participant{
    private static final int DEALER_MAX_HITTABLE_POINT = 16;

    protected Dealer(final ParticipantCards cards) {
        super(cards);
    }

    @Override
    protected boolean isHittable() {
        return getTotalPoint() <= DEALER_MAX_HITTABLE_POINT;
    }
}
