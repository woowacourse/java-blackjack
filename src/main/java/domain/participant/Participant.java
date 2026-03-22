package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import domain.rule.State;
import java.util.List;

public abstract class Participant {
    protected final Hand hand;

    protected Participant(Hand hand) {
        this.hand = hand;
    }

    public void draw(Card card) {
        hand.addCard(card);
    }

    public void stay() {
        hand.stay();
    }

    public boolean isBust() {
        return hand.getState().isBust();
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public State getState() {
        return hand.getState();
    }
}
