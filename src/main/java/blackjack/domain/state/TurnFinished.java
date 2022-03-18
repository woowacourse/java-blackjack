package blackjack.domain.state;

import blackjack.domain.card.Card;

public abstract class TurnFinished implements State {
    @Override
    public State stand() {
        throw new IllegalStateException("턴이 종료되어서 스탠드할 수 없습니다.");
    }

    @Override
    public State hit(Card card) {
        throw new IllegalStateException("턴이 종료되어서 히트할 수 없습니다.");
    }
}
