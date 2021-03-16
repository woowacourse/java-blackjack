package blackjack.domain.user;

import blackjack.domain.card.Cards;
import blackjack.domain.state.FirstTurn;
import blackjack.domain.state.State;

import java.util.ArrayList;

public abstract class User {
    protected final Cards cards;
    protected final Name name;

    protected State state;

    public User(String name){
        this(new Name(name));
    }

    public User(Name name) {
        this.cards = new Cards(new ArrayList<>());
        this.name = name;
    }

    public void drawInitialCards(Cards cards) {
        state = FirstTurn.generateState(cards);
    }

    public boolean isRunning() {
        return !this.state.isFinished();
    }

    public void hit(Cards cards) {
        changeState(state.draw(cards));
    }

    public void stay() {
        changeState(state.stay());
    }

    public void changeState(State state) {
        this.state = state;
    }

    public boolean isBlackjack() {
        return this.state.getCards().isBlackjack();
    }

    public boolean isBust() {
        return this.state.getCards().isBust();
    }

    public Cards cards() {
        return this.state.getCards();
    }

    public String name() {
        return this.name.getName();
    }

    public int score() {
        return this.state.getCards().calculateScore().getScore();
    }

    public State state() {
        return this.state;
    }

    public abstract boolean isHit();
}
