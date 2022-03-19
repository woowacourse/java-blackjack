package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Stay implements State {

    private final Cards cards;

    protected Stay(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        return null;
    }

    @Override
    public State stay() {
        return null;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Cards cards() {
        return null;
    }

    @Override
    public double earningRate(State state) {
        return 0;
    }
}
