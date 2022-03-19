package blackjack_statepattern.state;

import blackjack_statepattern.Card;
import blackjack_statepattern.Cards;

public abstract class Finished extends Started {

    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public final State draw(final Card card) {
        throw new IllegalArgumentException("더이상 드로우할 수 없습니다.");
    }

    @Override
    public final State stay() {
        throw new IllegalArgumentException("스테이 할 수 없습니다.");
    }

    @Override
    public final boolean isFinished() {
        return true;
    }

    @Override
    public double profit(final double money) {
        return money * earningRate();
    }

    protected abstract double earningRate();

}
