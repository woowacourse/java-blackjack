package domain.participant;

import domain.card.Card;
import domain.card.CurrentHand;
import java.util.List;

public abstract class Participant {

    private final ParticipantState state;

    protected Participant(final Name name) {
        this.state = new ParticipantState(name);
    }


    public void draw(final Card card) {
        state.draw(card);
    }


    public abstract boolean isDrawable();

    public boolean isBust() {
        return state.isBust();
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }


    public int getScore() {
        return state.getScore();
    }

    public String getName() {
        return state.getName();
    }

    public List<Card> getHand() {
        return state.getHand();
    }

    public CurrentHand getCurrentHand() {
        return state.getCurrentHand();
    }
}
