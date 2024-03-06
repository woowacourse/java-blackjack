package blackjack.fixture;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Hand;
import blackjack.domain.Player;

import static blackjack.domain.CardNumber.ACE;
import static blackjack.domain.CardNumber.KING;
import static blackjack.domain.CardNumber.QUEEN;
import static blackjack.domain.CardShape.HEART;
import static blackjack.domain.CardShape.SPADE;

public class PlayerFixture {
    public static Player playerA() {
        Hand hand = new Hand();
        hand.add(new Card(ACE, HEART));
        hand.add(new Card(KING, HEART));
        hand.add(new Card(KING, SPADE));

        return new Player(hand);
    }

    public static Player playerAWithEmptyHand() {
        return new Player(new Hand());
    }

    public static Player playerB() {
        Hand hand = new Hand();
        hand.add(new Card(QUEEN, HEART));
        hand.add(new Card(KING, HEART));

        return new Player(hand);
    }

    public static Player playerBWithEmptyHand() {
        return new Player(new Hand());
    }

    public static Dealer dealer() {
        Hand hand = new Hand();
        hand.add(new Card(ACE, HEART));
        hand.add(new Card(ACE, HEART));
        hand.add(new Card(KING, SPADE));

        return new Dealer(hand);
    }
}
