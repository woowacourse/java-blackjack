package fixture;

import domain.card.Card;
import domain.card.Hand;

public class HandFixture {
    public static Hand createHand(Card... cards) {
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.add(card);
        }
        return hand;
    }
}
