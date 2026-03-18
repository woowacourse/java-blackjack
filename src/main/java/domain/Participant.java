package domain;

import domain.card.Card;
import domain.state.Hit;
import domain.state.ParticipantState;

import java.util.List;

public abstract class Participant {
    protected final String name;
    protected ParticipantState state;

    protected Participant(String name) {
        this.name = name;
        this.state = new Hit(new Hand());
    }

    public void draw(Card card) {
        state = state.draw(card);
    }

    public void stay(){
        state = state.onStay();
    }

    public boolean isBurst() {
        return state.isBust();
    }

    public boolean isBlackJack() {
        return state.isBlackJack();
    }

    public abstract boolean canHit();

    public String getName() {
        return name;
    }

    public int getScore() {
        return state.getScore();
    }

    public List<Card> getCards() {
        return state.getCards();
    }

    public int getHandSize() {
        return state.getHandSize();
    }
}
