package domain.state;

import domain.Card;
import domain.Hand;

public class Bust extends Finished {

    public Bust(final Hand hand) {
        super(hand);
    }

    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException("버스트 된 플레이어는 카드를 뽑을 수 없습니다.");
    }

    @Override
    public double profitRate(final State state) {
        return -1;
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("버스트 된 플레이어는 스탠드 할 수 없습니다.");
    }
}
