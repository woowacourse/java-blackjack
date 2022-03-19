package blackjack.domain.state.started;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;
import blackjack.domain.state.State;

public abstract class Started implements State {

    protected final Cards cards;

    protected Started(Cards cards) {
        this.cards = cards;
    }

    @Override
    public abstract State draw(Card card);

    @Override
    public final Cards getCards() {
        return cards;
    }

    @Override
    public abstract State stay();

    @Override
    public boolean isFinished() {
        return false;
    }
}
