package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.state.State;
import blackjack.domain.state.running.Hit;

public abstract class Participant {

    private final String name;
    private State state;

    public Participant(String name) {
        this.name = name;
        this.state = new Hit();
    }

    public String getName() {
        return name;
    }

    public Cards cards() {
        return state.cards();
    }

    public int score() {
        return state.score();
    }

    public void initialDraw(Deck deck) {
        state = state.initialDraw(deck);
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public boolean isBust() {
        return state.isBust();
    }

    public abstract boolean canDraw();

    public boolean isFinished() {
        return state.isFinished();
    }

    public void draw(Card card) {
        this.state = state.draw(card);
    }

    public void stay() {
        this.state = state.stay();
    }

    public double earningRate() {
        return state.earningRate();
    }

}
