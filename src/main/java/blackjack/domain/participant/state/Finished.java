package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;
import java.util.List;

public abstract class Finished implements State {

    private final List<Card> cards;

    public Finished(final List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException();
    }

    @Override
    public State stay() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
