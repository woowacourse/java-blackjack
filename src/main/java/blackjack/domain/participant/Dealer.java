package blackjack.domain.participant;

import blackjack.domain.game.ParticipantCards;
import blackjack.domain.game.ResultType;

import static blackjack.domain.game.ResultType.*;

public class Dealer extends Participant {
    private static final int DEALER_MAX_HITTABLE_POINT = 16;
    private static final int BLACK_JACK_NUMBER = 21;
    private static final String DEFAULT_NAME = "딜러";

    public Dealer(final ParticipantCards cards) {
        super(cards, DEFAULT_NAME);
    }

    @Override
    public boolean isHittable() {
        return getTotalPoint() <= DEALER_MAX_HITTABLE_POINT;
    }

    public ResultType judgeResult(final Participant participant) {
        final int dealerPoint = getTotalPoint();
        final int participantPoint = participant.getTotalPoint();

        if (participantPoint > BLACK_JACK_NUMBER) {
            return WIN;
        }
        if (dealerPoint > BLACK_JACK_NUMBER || dealerPoint < participantPoint) {
            return LOSE;
        }
        if (dealerPoint > participantPoint) {
            return WIN;
        }
        return PUSH;
    }
}
