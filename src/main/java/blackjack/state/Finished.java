package blackjack.state;

import blackjack.domain.card.Card;

public abstract class Finished implements State {

    protected Cards cards;

    protected Finished(Cards cards) {
        this.cards = cards;
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public State hit(Card card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public State stay() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Score score() {
        return this.cards.score();
    }
}
