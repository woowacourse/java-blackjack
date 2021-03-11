package blackjack.domain.status;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public abstract class State {
    protected Cards cards;

    public State(Cards cards) {
        this.cards = cards;
    }

    public Cards getCards() {
        return cards;
    }

    public abstract State draw(Card card);

    public abstract boolean isFinished();
}
