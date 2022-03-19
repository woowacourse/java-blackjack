package blackjack_statepattern.state;

import blackjack_statepattern.Card;

public abstract class Finished implements State {

    @Override
    public final State draw(final Card card) {
        throw new IllegalArgumentException("더이상 드로우할 수 없습니다.");
    }

    @Override
    public final State stay() {
        throw new IllegalArgumentException("스테이 할 수 없습니다.");
    }
}
