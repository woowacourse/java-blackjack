package blackjackgame.domain.player;

import java.util.List;

import blackjackgame.domain.Score;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.state.Running;
import blackjackgame.domain.state.State;

public abstract class Player {
    private State state;

    protected Player(State state) {
        this.state = state;
    }

    protected abstract List<Card> startingCards();

    protected abstract boolean canHit();

    public abstract String getName();

    public List<Card> cards() {
        return state.cards();
    }

    protected final boolean isRunning() {
        return state instanceof Running;
    }

    public final int scoreValue() {
        return state.score().value();
    }

    protected final boolean isLessThan(int score) {
        return state.score().isLessThan(new Score(score));
    }

    public void draw(Card card) {
        state = state.draw(card);
    }

    protected void stay(){
        state = state.stay();
    }
}
