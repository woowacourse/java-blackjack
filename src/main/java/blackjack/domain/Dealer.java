package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

import java.util.List;

import static blackjack.domain.ResultType.*;

public class Dealer extends Participant {
    private static final int DEALER_MAX_HITTABLE_POINT = 16;
    private static final int INITIAL_OPEN_CARD_COUNT = 1;
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

    public ResultType judgePlayerResult(final Participant participant) {
        int dealerPoint = getTotalPoint();
        int participantPoint = participant.getTotalPoint();

        return judgeResult(participant, dealerPoint, participantPoint);
    }

    private ResultType judgeResult(final Participant participant, final int dealerPoint, final int participantPoint) {
        if(participant.isBlackJack() && !isBlackJack()) {
            return BLACK_JACK;
        }
        if(participant.isBust() || isDealerBlackJack(participant)) {
            return LOSE;
        }
        if (isDealerBust(dealerPoint, participantPoint)) {
            return WIN;
        }
        if (dealerPoint > participantPoint) {
            return LOSE;
        }
        return PUSH;
    }

    private boolean isDealerBlackJack(final Participant participant) {
        return isBlackJack() && !participant.isBlackJack();
    }

    private boolean isDealerBust(int dealerPoint, int participantPoint) {
        return isBust() || dealerPoint < participantPoint;
    }
}
