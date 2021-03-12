package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finish extends Started {
    protected Finish(Cards cards) {
        super(cards);
    }

    @Override
    public final State draw(Card card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final boolean isFinish() {
        return true;
    }

    @Override
    public final State stay() {
        throw new UnsupportedOperationException();
    }
}
