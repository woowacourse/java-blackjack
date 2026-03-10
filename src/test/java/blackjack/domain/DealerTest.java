package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러가_카드를_받는다() {
        Dealer dealer = new Dealer();
        dealer.recieveCard(new Card(CardPoint.ACE, CardPattern.DIAMOND));
        assertThat(dealer.getCardCount()).isEqualTo(1);
    }

    @Test
    void 딜러의_카드의_합이_17_이상이다_에이스_포함() {
        Dealer dealer = new Dealer();
        dealer.hand.addCard(new Card(CardPoint.ACE, CardPattern.DIAMOND));
        dealer.hand.addCard(new Card(CardPoint.SIX, CardPattern.CLUB));
        assertTrue(dealer.isOver17());
    }

    @Test
    void 딜러의_카드의_합이_17_이상이다_에이스_미포함() {
        Dealer dealer = new Dealer();
        dealer.hand.addCard(new Card(CardPoint.TEN, CardPattern.DIAMOND));
        dealer.hand.addCard(new Card(CardPoint.FIVE, CardPattern.CLUB));
        dealer.hand.addCard(new Card(CardPoint.FIVE, CardPattern.CLUB));
        assertTrue(dealer.isOver17());
    }

    @Test
    void 딜러의_카드의_합이_17_미만이다() {
        Dealer dealer = new Dealer();
        dealer.hand.addCard(new Card(CardPoint.TEN, CardPattern.DIAMOND));
        dealer.hand.addCard(new Card(CardPoint.FIVE, CardPattern.CLUB));
        assertFalse(dealer.isOver17());
    }
}
