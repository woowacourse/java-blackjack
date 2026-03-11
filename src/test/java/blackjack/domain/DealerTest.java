package blackjack.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러의_카드의_합이_17_미만이다() {
        // arrange
        Hand hand = new Hand();
        hand.addCard(new Card(CardPoint.TEN, CardPattern.DIAMOND));
        hand.addCard(new Card(CardPoint.FIVE, CardPattern.CLUB));

        // act
        Dealer dealer = new Dealer(hand);

        //assert
        assertTrue(dealer.canHit());
    }

    @Test
    void 딜러의_손패가_17이상인_경우카드를_받지_않는다() {
        // arrange
        Hand hand = new Hand();
        hand.addCard(new Card(CardPoint.TEN, CardPattern.DIAMOND));
        hand.addCard(new Card(CardPoint.FIVE, CardPattern.CLUB));
        hand.addCard(new Card(CardPoint.TWO, CardPattern.SPADE));

        // act
        Dealer dealer = new Dealer(hand);

        // assert
        assertFalse(dealer.canHit());
    }
}
