package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public abstract class Participant {
    private final Name name;
    private final Hand hand;

    public Participant(final Name name, final Hand hand) {
        this.name = name;
        this.hand = hand;
    }


    public void draw(final Card card) {
        hand.addCard(card);
    }


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
}
