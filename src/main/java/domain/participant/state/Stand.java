package domain.participant.state;

import domain.card.Card;
import domain.card.Hand;

public class Stand extends Finished {

    public static final int WIN_RATE = 1;
    public static final int LOSE_RATE = -1;
    public static final int EVEN_RATE = 0;

    public Stand(final Hand hand) {
        super(hand);
    }

    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException("Stand 상태의 플레이어는 hit 할 수 없습니다.");
    }

    @Override
    public double profitRate(final State state) {
        if (state.isBust()) {
            return WIN_RATE;
        }
        if (state.isBlackjack() || state.score() > score()) {
            return LOSE_RATE;
        }
        if (state.score() == score()) {
            return EVEN_RATE;
        }
        return WIN_RATE;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public State stand() {
        return this;
    }
}
