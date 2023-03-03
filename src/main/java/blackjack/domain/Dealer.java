package blackjack.domain;

import static blackjack.domain.ResultType.*;

public class Dealer extends Participant {
    private static final int DEALER_MAX_HITTABLE_POINT = 16;
    private static final String DEFAULT_NAME = "딜러";

    protected Dealer(final ParticipantCards cards) {
        super(cards, DEFAULT_NAME);
    }

    @Override
    protected boolean isHittable() {
        return getTotalPoint() <= DEALER_MAX_HITTABLE_POINT;
    }

    public ResultType judgeResult(final Participant participant) {
        int dealerPoint = getTotalPoint();
        int participantPoint = participant.getTotalPoint();

        if (participantPoint > 21) {
            return WIN;
        }
        if (dealerPoint > 21 || dealerPoint < participantPoint) {
            return LOSE;
        }
        if (dealerPoint > participantPoint) {
            return WIN;
        }
        return PUSH;
    }
}
