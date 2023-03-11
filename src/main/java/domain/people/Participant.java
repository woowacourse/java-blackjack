package domain.people;

import java.util.List;

import domain.card.Card;
import domain.card.Hand;

public class Participant {
    private static final int BLACKJACK_VALUE = 21;
    public static final int BUST_HAND_VALUE = 0;

    private final Hand hand;
    private final String name;

    public Participant(List<Card> cards, String name) {
        this.hand = new Hand(cards);
        this.name = name;
    }

    public void receiveCard(Card card) {
        hand.addCard(card);
    }

    public int fetchHandValue() {
        return hand.calculateHandValue();
    }

    public List<Card> fetchHand() {
        return hand.getHand();
    }

    public String getName() {
        return name;
    }

    public boolean isBust() {
        return hand.isBust();
    }
}
