package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.state.Ready;
import blackjack.domain.card.state.State;

import java.util.List;

public abstract class Participant implements CardReceivable {

    protected State state;

    protected Participant() {
        this.state = new Ready();
    }

    protected Participant(final Cards cards) {
        this.state = initializeState(cards.toList());
    }

    private State initializeState(final List<Card> cards) {
        State initializeState = new Ready();
        for (final Card card : cards) {
            initializeState = initializeState.draw(card);
        }
        return initializeState;
    }

    public void stand() {
        this.state = this.state.stand();
    }

    public List<Card> getCards() {
        return this.state.getCards();
    }

    public int calculateScore() {
        return this.state.calculate();
    }

    public void drawCard(final Card card) {
        this.state = this.state.draw(card);
    }

    public boolean isBlackjack() {
        return this.state.isBlackjack();
    }

    public boolean isBust() {
        return this.state.isBust();
    }

    public abstract String getNameAsString();


    public boolean isStand() {
        return this.state.isStand();
    }
}
