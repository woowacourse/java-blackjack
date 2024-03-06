package blackjack.fixture;

import blackjack.domain.Card;
import blackjack.domain.Hand;
import blackjack.domain.Player;

import static blackjack.domain.CardNumber.ACE;
import static blackjack.domain.CardNumber.KING;
import static blackjack.domain.CardNumber.QUEEN;
import static blackjack.domain.CardShape.HEART;
import static blackjack.domain.CardShape.SPADE;

public class PlayerFixture {
    public static Player mangcho() {
        Hand hand = new Hand();
        hand.add(new Card(ACE, HEART));
        hand.add(new Card(KING, HEART));
        hand.add(new Card(KING, SPADE));

        return new Player(hand);
    }

    public static Player ddang() {
        Hand hand = new Hand();
        hand.add(new Card(QUEEN, HEART));
        hand.add(new Card(KING, HEART));

        return new Player(hand);
    }

    public static Player dealer() {
        Hand hand = new Hand();
        hand.add(new Card(ACE, HEART));
        hand.add(new Card(ACE, HEART));
        hand.add(new Card(KING, SPADE));

        return new Player(hand);
    }
}
