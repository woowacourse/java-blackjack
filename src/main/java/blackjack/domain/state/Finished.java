package blackjack.domain.state;

import blackjack.domain.card.PlayingCard;

public abstract class Finished extends Started {
    public Finished(final Cards cards) {
        super(cards);
    }

    @Override
    public State draw(final PlayingCard card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit(double money) {
        return money * earningRate();
    }

    public abstract double earningRate();
}
