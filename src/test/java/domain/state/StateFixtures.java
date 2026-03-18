package domain.state;

import domain.Hand;
import domain.card.Card;

public class StateFixtures {
    public static Hand handOf(Card... cards) {
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.addCard(card);
        }
        return hand;
    }
}