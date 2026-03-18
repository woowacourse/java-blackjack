package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public class Participant {

    protected final Name name;
    protected final Hand hand;

    public Participant(Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() {
        return name.value();
    }

    public Hand getHand() {
        return hand;
    }

    public void addCard(List<Card> cards) {
        for (Card card : cards) {
            hand.addCard(card);
        }
    }

    public int calculateScore() {
        return hand.calculateScore();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }
}
