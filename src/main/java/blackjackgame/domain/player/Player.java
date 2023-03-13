package blackjackgame.domain.player;

import java.util.List;

import blackjackgame.domain.Score;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.state.BlackJack;
import blackjackgame.domain.state.Bust;
import blackjackgame.domain.state.Running;
import blackjackgame.domain.state.State;
import blackjackgame.domain.state.Stay;

public abstract class Player {
    private State state;

    protected Player(State state) {
        this.state = state;
    }

    public abstract List<Card> startingCards();

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

    public final boolean isLessThan(int score) {
        return state.score().isLessThan(new Score(score));
    }

    public void draw(Card card) {
        state = state.draw(card);
    }

    protected void stay(){
        state = state.stay();
    }

    public boolean isBust(){
        return state instanceof Bust;
    }

    public boolean isBlackJack(){
        return state instanceof BlackJack;
    }

    public boolean isStay(){
        return state instanceof Stay;
    }
}
