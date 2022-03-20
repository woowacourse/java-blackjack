package blackjack.domain.state;

import blackjack.domain.card.Card;

abstract class Finished extends Running {

    static final float BLACKJACK_MULTIPLIER = 1.5f;
    static final int WIN_MULTIPLIER = 1;
    static final int LOSE_MULTIPLIER = -1;
    static final int PUSH_MULTIPLIER = 0;

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
    public boolean isDrawable() {
        return false;
    }
}
