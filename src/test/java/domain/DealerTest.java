package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class DealerTest {
    @Test
    @DisplayName("딜러는 고유 숫자값 총합이 16이하 여부를 반환")
    void test1() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.JACK, Suit.SPADE));
        dealer.addCard(new Card(Denomination.TEN, Suit.SPADE));

        assertThat(dealer.isBelowThreshold()).isFalse();
    }

    @Test
    @DisplayName("딜러의 핸드 총합이 16 이하면 카드를 추가로 받는다.")
    void test2() {
        Dealer dealer = new Dealer();

        dealer.addCard(new Card(Denomination.TWO, Suit.CLUB));
        dealer.addCard(new Card(Denomination.TEN, Suit.DIAMOND));

        dealer.drawWithThreshold(new Deck());

        assertThat(dealer.getCardCount()).isGreaterThan(2);
    }

}
