package blackjack.fixture;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Player;

public class PlayerFixture {
    public static Player player(Card... cards) {
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.add(card);
        }
        return new Player(hand);
    }

    public static Dealer dealer(Card... cards) {
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.add(card);
        }
        return new Dealer(hand);
    }
}
