package domain.participant;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("hit 처리 시, 1장을 뽑는다.")
    void Hand에_1장_추가() {
        // given
        Dealer dealer = new Dealer();

        // when
        dealer.receive(new Card(Rank.ACE, Suit.DIAMOND));

        // then
        Assertions.assertEquals(dealer.getCards().size(), 1);
    }

    @Test
    @DisplayName("16이하 시, 무조건 hit")
    void hit_16이하() {
        // given
        Dealer dealer = new Dealer();
        dealer.receive(new Card(Rank.ACE, Suit.DIAMOND));
        dealer.receive(new Card(Rank.FIVE, Suit.CLOVER));

        // when - then
        Assertions.assertTrue(dealer.shouldHit());
    }

    @Test
    @DisplayName("17 이상 시, 무조건 stand")
    void stand_17이상() {
        // given
        Dealer dealer = new Dealer();
        dealer.receive(new Card(Rank.SEVEN, Suit.DIAMOND));
        dealer.receive(new Card(Rank.JACK, Suit.CLOVER));

        // when - then
        Assertions.assertFalse(dealer.shouldHit());
    }

    @Test
    @DisplayName("딜러는 첫 번째 카드만 보여준다.")
    void 첫장_오픈() {
        // given
        Dealer dealer = new Dealer();
        Card firstCard = new Card(Rank.SEVEN, Suit.DIAMOND);
        dealer.receive(firstCard);
        dealer.receive(new Card(Rank.JACK, Suit.CLOVER));

        // when - then
        Assertions.assertEquals(dealer.getFirstCard(), firstCard);
    }
}
