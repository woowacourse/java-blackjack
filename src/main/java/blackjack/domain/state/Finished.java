package blackjack.domain.state;

import blackjack.domain.card.Card;

public abstract class Finished implements State {
    @Override
    public State draw(Card card) {
        throw new IllegalStateException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profit(double money) {
        return money;
    }
}
