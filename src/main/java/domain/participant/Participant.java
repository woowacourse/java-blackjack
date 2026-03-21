package domain.participant;

import domain.card.Card;
import domain.card.Cards;
import domain.rule.Hit;
import domain.rule.State;
import java.util.List;

public abstract class Participant {
    protected State state;

    public Participant(Cards cards) {
        this.state = Hit.of(cards);
    }

    public void draw(Card card) {
        state = state.draw(card);
    }

    public void stay() {
        state = state.stay();
    }

    public boolean isBust() {
        return state.isBust();
    }

    public int calculateScore() {
        return state.cards().calculateScore();
    }

    public List<Card> getHand() {
        return state.cards().getHand();
    }

    public State getState() {
        return state;
    }
}
