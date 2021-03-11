package blackjack.state;

import blackjack.domain.Dealer;
import blackjack.domain.card.Card;

public abstract class Finished extends Started {

    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
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
    public double profit(double money, Dealer dealer) {
        return money * earningRate(dealer);
    }

    public abstract double earningRate(Dealer dealer);
}
