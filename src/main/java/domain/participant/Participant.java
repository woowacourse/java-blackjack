package domain.participant;

import domain.Card;
import domain.Hand;

import java.util.List;

public abstract class Participant {
    private String name;
    protected Hand hand;

    protected Participant(String name, Hand hand) {
        this.name = name;
        this.hand = hand;
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean canReceiveCard() {
        return hand.canHit();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public List<String> showHand() {
        return hand.showHand();
    }
}
