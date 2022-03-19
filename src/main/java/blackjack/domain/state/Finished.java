package blackjack.domain.state;

import blackjack.domain.card.Card;

abstract class Finished extends Running {

    Finished(Cards cards) {
        super(cards);
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException("Finished 상태에선 카드를 더 받을 수 없습니다.");
    }

    @Override
    public final State stay() {
        throw new IllegalStateException("Finished 상태에선 상태를 변경할 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
