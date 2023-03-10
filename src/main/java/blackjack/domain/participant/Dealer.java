package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.game.ResultType;

import static blackjack.domain.game.ResultType.*;

public class Dealer extends Participant {
    private static final int DEALER_MAX_HITTABLE_POINT = 16;
    private static final int BLACK_JACK_NUMBER = 21;
    private static final String DEFAULT_NAME = "딜러";

    public Dealer(final Deck deck) {
        super(deck, DEFAULT_NAME);
    }

    @Override
    public boolean isHittable() {
        return getTotalPoint() <= DEALER_MAX_HITTABLE_POINT;
    }

    public ResultType judgeResult(final Participant participant) {
        final int dealerPoint = getTotalPoint();
        final int participantPoint = participant.getTotalPoint();
        return compare(dealerPoint, participantPoint);
    }

    private ResultType compare(final int dealerPoint, final int participantPoint) {
        if (checkBlackJack(dealerPoint, participantPoint)) {
            return BLACK_JACK;
        }
        if (checkWin(dealerPoint, participantPoint)) {
            return WIN;
        }
        if (checkLose(dealerPoint, participantPoint)) {
            return LOSE;
        }
        return PUSH;
    }

    private boolean checkBlackJack(final int dealerPoint, final int participantPoint) {
        return participantPoint == BLACK_JACK_NUMBER && dealerPoint != BLACK_JACK_NUMBER;
    }

    private boolean checkWin(final int dealerPoint, final int participantPoint) {
        return dealerPoint < participantPoint && participantPoint < BLACK_JACK_NUMBER;
    }

    private boolean checkLose(final int dealerPoint, final int participantPoint) {
        return participantPoint > BLACK_JACK_NUMBER
                || participantPoint < dealerPoint && dealerPoint <= BLACK_JACK_NUMBER
                || participantPoint == dealerPoint && participantPoint != BLACK_JACK_NUMBER;
    }
}
