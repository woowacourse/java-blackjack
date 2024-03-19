package domain.participant.state;

import domain.card.Card;
import domain.card.Hand;

public class Bust extends Finished {

    public static final int BUST_RATE = -1;

    public Bust(final Hand hand) {
        super(hand);
    }

    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException("버스트 된 플레이어는 카드를 뽑을 수 없습니다.");
    }

    @Override
    public double profitRate(final State state) {
        return BUST_RATE;
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("버스트 된 플레이어는 스탠드 할 수 없습니다.");
    }
}
