package domain.state;

import domain.Card;
import domain.Hand;

public class Blackjack implements State {
    private final Hand hand;

    public Blackjack(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public State hit(final Card card) {
        throw new UnsupportedOperationException("블랙잭 상태의 플레이어는 카드를 뽑을 수 없습니다.");
    }

    @Override
    public State stand() {
        return this;
    }
}
