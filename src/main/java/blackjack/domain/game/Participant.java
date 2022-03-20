package blackjack.domain.game;

import blackjack.domain.Name;
import blackjack.domain.card.Card;
import blackjack.domain.state.Cards;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;

public abstract class Participant {

    private State state;
    private final Name name;

    Participant(Name name) {
        this.state = new Ready();
        this.name = name;
    }

    final void init(Card firstCard, Card secondCard) {
        draw(firstCard);
        draw(secondCard);
    }

    final void draw(Card card) {
        this.state = state.draw(card);
    }

    public abstract boolean isDrawable();

    void stay() {
        state = state.stay();
    }

    final State getState() {
        return state;
    }

    final boolean isLowerScoreThan(Participant other) {
        return getScore() < other.getScore();
    }

    final boolean isBlackjack() {
        return getCards().isBlackjack();
    }

    final boolean isBust() {
        return getCards().isBust();
    }

    public final Cards getCards() {
        return state.getCards();
    }

    public final Name getName() {
        return name;
    }

    public final int getScore() {
        return getCards().sum();
    }
}
