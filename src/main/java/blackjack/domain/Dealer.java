package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

import java.util.List;

import static blackjack.domain.ResultType.*;

public class Dealer extends Participant {
    private static final int DEALER_MAX_HITTABLE_POINT = 16;
    private static final int INITIAL_OPEN_CARD_COUNT = 1;
    private static final int MAX_SCORE_NOT_BUST = 21;
    private static final String DEFAULT_NAME = "딜러";

    protected Dealer(final ParticipantCards cards) {
        super(cards, DEFAULT_NAME);
    }

    @Override
    public List<Card> initialOpen() {
        return cards.open(INITIAL_OPEN_CARD_COUNT);
    }

    @Override
    protected boolean isHittable() {
        return getTotalPoint() <= DEALER_MAX_HITTABLE_POINT;
    }

    public ResultType judgeResult(final Participant participant) {
        int dealerPoint = getTotalPoint();
        int participantPoint = participant.getTotalPoint();

        if (participantPoint > MAX_SCORE_NOT_BUST) {
            return WIN;
        }
        if (isDealerBust(dealerPoint, participantPoint)) {
            return LOSE;
        }
        if (dealerPoint > participantPoint) {
            return WIN;
        }
        return PUSH;
    }

    private boolean isDealerBust(int dealerPoint, int participantPoint) {
        return dealerPoint > MAX_SCORE_NOT_BUST || dealerPoint < participantPoint;
    }
}
