package domain.participant.state;

import domain.card.Card;
import domain.card.Hand;

public class Blackjack extends Finished {
    public static final int LOSE_RATE = 0;
    public static final double WIN_RATE = 1.5;

    public Blackjack(final Hand hand) {
        super(hand);
    }

    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException("블랙잭 상태의 플레이어는 카드를 뽑을 수 없습니다.");
    }

    @Override
    public double profitRate(final State state) {
        if (state instanceof Blackjack) {
            return LOSE_RATE;
        }
        return WIN_RATE;
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public State stand() {
        return this;
    }
}
