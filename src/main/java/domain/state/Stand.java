package domain.state;

import domain.Card;
import domain.Hand;

public class Stand extends Finished {
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
            return 1;
        }
        if (state.isBlackjack() || state.score() > score()) {
            return -1;
        }
        if (state.score() == score()) {
            return 0;
        }
        return 1;
    }

    @Override
    public State stand() {
        return this;
    }
}
