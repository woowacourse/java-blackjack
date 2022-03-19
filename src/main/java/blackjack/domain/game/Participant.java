package blackjack.domain.game;

import blackjack.domain.Name;
import blackjack.domain.card.Card;
import blackjack.domain.state.Cards;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;

public abstract class Participant {

    private State state;

    Participant() {
        this.state = new Ready();
    }

    final void init(Card firstCard, Card secondCard) {
        draw(firstCard);
        draw(secondCard);
    }

    final State getState() {
        return state;
    }

    final void draw(Card card) {
        this.state = state.draw(card);
    }

    public abstract Name getName();

    public final Cards getCards() {
        return state.getCards();
    }

    public final int getScore() {
        return getCards().sum();
    }

    void stay() {
        state = state.stay();
    }

    public abstract boolean isDrawable();
}
