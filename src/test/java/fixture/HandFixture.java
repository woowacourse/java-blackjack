package fixture;

import blackjack.card.Card;
import blackjack.card.Hand;
import java.util.Arrays;

public class HandFixture {
    public static Hand createHandOf(Card... cards) {
        Hand hand = new Hand();
        Arrays.stream(cards)
                .forEach(hand::add);

        return hand;
    }
}
