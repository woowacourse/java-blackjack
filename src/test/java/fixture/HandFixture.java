package fixture;

import domain.Card;
import domain.Hand;

public class HandFixture {
    public static Hand createHand(Card... cards) {
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.add(card);
        }
        return hand;
    }
}
