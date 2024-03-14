package domain.state;

import domain.Card;
import domain.Hand;

public class Bust implements State {
    private final Hand hand;

    public Bust(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public State hit(final Card card) {
        throw new UnsupportedOperationException("버스트 된 플레이어는 카드를 뽑을 수 없습니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("버스트 된 플레이어는 스탠드 할 수 없습니다.");
    }
}
