package blackjack.domain.state;

import blackjack.domain.Deck;
import blackjack.domain.Hand;

public abstract class ClosedState extends State {

    public ClosedState(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Deck deck) {
        throw new UnsupportedOperationException("턴이 종료되어 드로우할 수 없는 상태입니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("턴이 종료되어 스탠드할 수 없는 상태입니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
