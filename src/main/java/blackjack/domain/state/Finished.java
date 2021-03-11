package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;

public abstract class Finished extends Started {
    protected static final int DRAW_AMOUNT = 0;

    protected Finished(PlayerCards cards) {
        super(cards);
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException("이미 끝난 상태입니다.");
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("카드를 뽑을 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    protected abstract double earningRate();
}
