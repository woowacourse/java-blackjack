package blackjack.fixture;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Name;
import blackjack.domain.Player;

public class PlayerFixture {
    private static final Name name = new Name("player");

    public static Player player(Card... cards) {
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.add(card);
        }
        return new Player(name, hand);
    }

    public static Dealer dealer(Card... cards) {
        Hand hand = new Hand();
        for (Card card : cards) {
            hand.add(card);
        }
        return new Dealer(name, hand);
    }
}
