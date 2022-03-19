package blackjack.domain.state.running;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;
import blackjack.domain.state.State;

public abstract class Running implements State {

    protected final Cards cards;

    protected Running(Cards cards) {
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
}
