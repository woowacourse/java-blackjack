package domain.participant;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public class Participant {
    private final Name name;
    private final Hand hand;
    private final boolean isDealer;

    public Participant(final Name name, final Hand hand, final boolean isDealer) {
        this.name = name;
        this.hand = hand;
        this.isDealer = isDealer;
    }

    public void addHandCard(final Card card) {
        hand.addCard(card);
    }

    public boolean isDealer() {
        return isDealer;
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

    public List<Card> getHandCards() {
        return hand.getHandCards();
    }
}
