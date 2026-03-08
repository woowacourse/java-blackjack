package testutil;

import domain.Card;
import domain.Hand;

import java.util.List;

public final class HandTestUtil {
    public static Hand createHand(List<Card> cards) {
        Hand hand = new Hand();
        cards.forEach(hand::add);
        return hand;
    }
}
