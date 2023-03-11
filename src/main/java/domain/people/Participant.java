package domain.people;

import java.util.List;

import domain.card.Card;
import domain.card.Hand;

public class Participant {
    private final Hand hand;
    private final String name;

    public Participant(List<Card> cards, String name) {
        this.hand = new Hand(cards);
        this.name = name;
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public List<Card> fetchHand() {
        return hand.getHand();
    }

    public int fetchHandValue() {
        return hand.calculateHandValue();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public String getName() {
        return name;
    }
}
