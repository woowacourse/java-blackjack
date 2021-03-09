package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finished extends Started {
    public Finished(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException("[ERROR] 더 이상 카드를 받을 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit(final double money) {
        return money * earningRate();
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException();
    }

    public abstract double earningRate();
}
