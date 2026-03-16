package domain.participant;

import domain.card.Card;
import domain.card.CurrentHand;
import domain.card.Hand;
import java.util.List;

public abstract class Participant {
    private final Name name;
    private final Hand hand;

    public Participant(final Name name) {
        this.name = name;
        this.hand = new Hand();
    }


    public void draw(final Card card) {
        hand.addCard(card);
    }


    public abstract boolean isDrawable();

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }


    public int getScore() {
        return hand.getScore();
    }

    public String getName() {
        return name.name();
    }

    public List<Card> getHand() {
        return hand.getHand();
    }

    public CurrentHand getCurrentHand() {
        return new CurrentHand(getName(), getHand());
    }
}
