package blackjack.domain.state;

import blackjack.domain.card.Card;

public class Bust implements State{

    @Override
    public boolean isEndState() {
        return true;
    }

    @Override
    public double profit() {
        return 0;
    }

    @Override
    public void draw(Card card) {

    }

    @Override
    public State changeState() {
        return null;
    }

    @Override
    public State stay() {
        return this;
    }
}
