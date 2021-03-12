package blackjack.domain.state;

import blackjack.domain.card.Card;

public abstract class Finished implements State {
    @Override
    public State draw(final Card card) {
        throw new IllegalStateException("더 이상 카드를 받을 수 없는 상태입니다.");
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
